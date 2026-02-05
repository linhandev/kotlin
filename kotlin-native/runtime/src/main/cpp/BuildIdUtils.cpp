#include "BuildIdUtils.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <iomanip>
#include <unistd.h>
#include <cstring>

#if USE_ELF_SYMBOLS
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>

struct Elf64_Ehdr {
    uint8_t  e_ident[16];
    uint16_t e_type;
    uint16_t e_machine;
    uint32_t e_version;
    uint64_t e_entry;
    uint64_t e_phoff;
    uint64_t e_shoff;
    uint32_t e_flags;
    uint16_t e_ehsize;
    uint16_t e_phentsize;
    uint16_t e_phnum;
    uint16_t e_shentsize;
    uint16_t e_shnum;
    uint16_t e_shstrndx;
};

// ELF节区头结构
struct Elf64_Shdr {
    uint32_t sh_name;
    uint32_t sh_type;
    uint64_t sh_flags;
    uint64_t sh_addr;
    uint64_t sh_offset;
    uint64_t sh_size;
    uint32_t sh_link;
    uint32_t sh_info;
    uint64_t sh_addralign;
    uint64_t sh_entsize;
};

struct NoteHeader {
    uint32_t n_namesz;
    uint32_t n_descsz;
    uint32_t n_type;
};

std::string BuildIdUtils::getMapsFilePath(pid_t target_pid) {
    pid_t pid = (target_pid == 0) ? getpid() : target_pid;
    return "/proc/" + std::to_string(pid) + "/maps";
}

std::vector<MapsEntry> BuildIdUtils::parseMapsFile(pid_t target_pid) {
    std::vector<MapsEntry> maps_entries;
    std::string maps_path = getMapsFilePath(target_pid);

    std::ifstream maps_file(maps_path);
    if (!maps_file.is_open()) {
        return maps_entries;
    }

    std::string line;
    while (std::getline(maps_file, line)) {
        size_t space_pos = line.find(' ');
        if (space_pos == std::string::npos) continue;
        std::string addr_range = line.substr(0, space_pos);

        size_t dash_pos = addr_range.find('-');
        if (dash_pos == std::string::npos) continue;
        std::string start_str = addr_range.substr(0, dash_pos);
        std::string end_str = addr_range.substr(dash_pos + 1);

        uintptr_t start_addr, end_addr;
        try {
            start_addr = std::stoull(start_str, nullptr, 16);
            end_addr = std::stoull(end_str, nullptr, 16);
        } catch (...) {
            continue;
        }

        size_t last_space_pos = line.find_last_of(" \t");
        if (last_space_pos == std::string::npos) continue;
        std::string so_path = line.substr(last_space_pos + 1);

        if (so_path.empty() || so_path[0] != '/') continue;

        maps_entries.push_back({start_addr, end_addr, so_path});
    }

    maps_file.close();
    return maps_entries;
}

std::string BuildIdUtils::findSoPathFromMaps(uintptr_t address, const std::vector<MapsEntry>& maps_entries) {
    for (const auto& entry : maps_entries) {
        if (address >= entry.start_addr && address < entry.end_addr) {
            return entry.so_path;
        }
    }
    return "";
}


std::string BuildIdUtils::buildIdToString(const std::vector<uint8_t>& buildId) {
    std::stringstream ss;
    ss << std::hex << std::setfill('0');
    for (uint8_t b : buildId) {
        ss << std::setw(2) << static_cast<int>(b);
    }
    return ss.str();
}

std::string BuildIdUtils::getSoBuildId(const std::string& soPath, std::vector<uint8_t>& buildId) {
    int fd = open(soPath.c_str(), O_RDONLY);
    if (fd < 0) {
        return "";
    }

    struct stat fdStat;
    if (fstat(fd, &fdStat) < 0) {
        close(fd);
        return "";
    }

    void* mapped = mmap(nullptr, fdStat.st_size, PROT_READ, MAP_PRIVATE, fd, 0);
    close(fd);

    if (mapped == MAP_FAILED) {
        return "";
    }

    Elf64_Ehdr* elfHeader = reinterpret_cast<Elf64_Ehdr*>(mapped);
    if (elfHeader->e_ident[0] != 0x7f || elfHeader->e_ident[1] != 'E' ||
        elfHeader->e_ident[2] != 'L' || elfHeader->e_ident[3] != 'F') {
        munmap(mapped, fdStat.st_size);
        return "";
    }

    bool found = false;
    for (int i = 0; i < elfHeader->e_shnum; ++i) {
        Elf64_Shdr* section = reinterpret_cast<Elf64_Shdr*>(
                reinterpret_cast<char*>(mapped) + elfHeader->e_shoff +
                i * elfHeader->e_shentsize
        );

        if (section->sh_type == 7) {
            char* noteStart = reinterpret_cast<char*>(mapped) + section->sh_offset;
            char* noteEnd = noteStart + section->sh_size;
            char* current = noteStart;

            while (current + sizeof(NoteHeader) <= noteEnd) {
                NoteHeader* note = reinterpret_cast<NoteHeader*>(current);

                if (note->n_type == 3 && note->n_namesz == 4) {
                    current += sizeof(NoteHeader);
                    if (current + 4 > noteEnd) break;

                    current += (note->n_namesz + 3) & ~3;
                    if (current + note->n_descsz > noteEnd) break;

                    buildId.assign(current, current + note->n_descsz);
                    found = true;
                    return buildIdToString(buildId);
                }

                uint64_t skip = sizeof(NoteHeader);
                skip += (note->n_namesz + 3) & ~3;
                skip += (note->n_descsz + 3) & ~3;
                current += skip;
            }

            if (found) break;
        }
    }

    munmap(mapped, fdStat.st_size);
    return "";
}
#endif
