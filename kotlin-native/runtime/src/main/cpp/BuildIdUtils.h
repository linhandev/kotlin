#ifndef BUILD_ID_UTILS_H
#define BUILD_ID_UTILS_H

#include <vector>
#include <string>
#include <cstdint>

struct MapsEntry {
    uintptr_t start_addr;
    uintptr_t end_addr;
    std::string so_path;
};

class BuildIdUtils {
public:
    static std::vector<MapsEntry> parseMapsFile(pid_t target_pid = 0);

    static std::string findSoPathFromMaps(uintptr_t address, const std::vector<MapsEntry>& maps_entries);

    static std::string getSoBuildId(const std::string& so_path, std::vector<uint8_t>& build_id);

    static std::string buildIdToString(const std::vector<uint8_t>& build_id);

private:
    static std::string getMapsFilePath(pid_t target_pid);
};

#endif