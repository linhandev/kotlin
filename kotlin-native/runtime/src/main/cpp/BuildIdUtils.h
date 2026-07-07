#ifndef BUILD_ID_UTILS_H
#define BUILD_ID_UTILS_H

#include <vector>
#include <string>
#include <cstdint>

struct MapsEntry {
    uintptr_t startAddr;
    uintptr_t endAddr;
    std::string soPath;
};

class BuildIdUtils {
public:
    static std::vector<MapsEntry> parseMapsFile(pid_t target_pid = 0);

    static std::string findSoPathFromMaps(uintptr_t address, const std::vector<MapsEntry>& maps_entries);

    static std::string getSoBuildId(const std::string& soPath, std::vector<uint8_t>& buildId);

    static std::string buildIdToString(const std::vector<uint8_t>& buildId);

private:
    static std::string getMapsFilePath(pid_t target_pid);
};

#endif