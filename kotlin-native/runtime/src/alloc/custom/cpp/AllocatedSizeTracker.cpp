/*
 * Copyright 2022-2024 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */
#include "AllocatedSizeTracker.hpp"
#include "AllocatorImpl.hpp"
#include "GlobalData.hpp"
#include <fcntl.h>
#include <unistd.h>
#include <cerrno>
#include "Memory.h"
#include "CustomLogging.hpp"
#include "KAssert.h"
#include <ctime>
#include <cstring>
#include <iomanip>
#include <sstream>
#include <vector>
#include <algorithm>
#include <dirent.h>
#include <sys/stat.h>
#include <climits>
#include <cstdlib>
#include <cstdio>


// #region agent log - direct hilog bypass for debug session aed83d
#ifdef KONAN_OHOS
#include <hilog/log.h>
#include "hidebug/hidebug.h"
#include "hidebug/hidebug_type.h"
#include "hiappevent/hiappevent.h"
#define DBG_OOM(fmt, ...) OH_LOG_Print(LOG_APP, LOG_ERROR, 0xFF00, "OOM_DEBUG_aed83d", fmt, ##__VA_ARGS__)
#else
#define DBG_OOM(fmt, ...) ((void)0)
#endif

// #endregion
using namespace kotlin;

namespace {
void (*schedulerNotificationTestHook)(std::size_t) = nullptr;
constexpr const char K_DUMP_FILE_EXTENSION[] = ".dump";
constexpr std::size_t K_DUMP_FILE_EXTENSION_LENGTH = sizeof(K_DUMP_FILE_EXTENSION) - 1;

#ifdef KONAN_OHOS
#ifndef KOTLIN_NATIVE_HIAPPEVENT_FW_VERSION
#define KOTLIN_NATIVE_HIAPPEVENT_FW_VERSION "2.2.21-0.1.0"
#endif

static void ReportOomEventViaHiAppEvent(
        const char* dumpPath,
        std::size_t memUsage,
        std::size_t threshold,
        const char* timestamp,
        bool dumpSuccess) {
    std::ostringstream desc;
    desc << "Kotlin/Native heap over OOM threshold; dump_path=" << dumpPath << "; memory_usage=" << memUsage
         << "; oom_threshold=" << threshold << "; timestamp=" << timestamp << "; dump_success=" << (dumpSuccess ? "true" : "false");
    std::string descriptionStr = desc.str();

    OH_HiAppEvent_ReportFrameworkMemAnomaly(OH_KMP_KOTLIN, KOTLIN_NATIVE_HIAPPEVENT_FW_VERSION,
                                            descriptionStr.c_str());
    DBG_OOM("HiAppEvent: ReportFrameworkMemAnomaly invoked for KMP Kotlin.");
}
#else
static void ReportOomEventViaHiAppEvent(const char*, std::size_t, std::size_t, const char*, bool) {
    // No-op on non-OHOS platforms
}
#endif

// Get all dump files in directory, sorted by mtime (oldest first).
std::vector<std::string> GetSortedDumpFiles(const std::string& directory) {
    std::vector<std::string> dumpFiles;
    DIR* dir = opendir(directory.c_str());
    if (dir == nullptr) {
        DBG_OOM("Failed to open directory %{public}s for reading dump files", directory.c_str());
        return dumpFiles;
    }

    struct dirent* entry;
    while ((entry = readdir(dir)) != nullptr) {
        std::string filename = entry->d_name;
        // Match oom_dump_YYYYMMDD_HHMMSS.dump
        if (filename.find("oom_dump_") == 0 && filename.size() >= K_DUMP_FILE_EXTENSION_LENGTH &&
            filename.compare(filename.size() - K_DUMP_FILE_EXTENSION_LENGTH, K_DUMP_FILE_EXTENSION_LENGTH, K_DUMP_FILE_EXTENSION) == 0) {
            dumpFiles.push_back(directory + "/" + filename);
        }
    }
    closedir(dir);

    // Sort by mtime ascending.
    std::sort(dumpFiles.begin(), dumpFiles.end(), [](const std::string& a, const std::string& b) {
        struct stat statA;
        struct stat statB;
        int ra = stat(a.c_str(), &statA);
        int rb = stat(b.c_str(), &statB);
        if (ra != 0 && rb != 0) {
            return a < b;
        }
        if (ra != 0) {
            return true;
        }
        if (rb != 0) {
            return false;
        }
        if (statA.st_mtime != statB.st_mtime) {
            return statA.st_mtime < statB.st_mtime;
        }
        return a < b;
    });

    return dumpFiles;
}

// Remove oldest dump files so total count does not exceed maxFiles.
void CleanupOldDumpFiles(const std::string& directory, int maxFiles) {
    auto dumpFiles = GetSortedDumpFiles(directory);
    int filesToDelete = static_cast<int>(dumpFiles.size()) - maxFiles;

    for (int i = 0; i < filesToDelete && i < (int)dumpFiles.size(); ++i) {
        if (unlink(dumpFiles[i].c_str()) == 0) {
            DBG_OOM("Deleted old dump file: %{public}s", dumpFiles[i].c_str());
        } else {
            DBG_OOM("Failed to delete old dump file: %{public}s, errno: %{public}d",
                             dumpFiles[i].c_str(), errno);
        }
    }
}

std::string ToHostVisiblePath(const std::string& path) {
#ifdef KONAN_OHOS
    constexpr const char kSandboxPrefix[] = "/data/storage/el2/base/";
    if (path.rfind(kSandboxPrefix, 0) == 0) {
        FILE* mountInfo = fopen("/proc/self/mountinfo", "r");
        if (mountInfo != nullptr) {
            char* line = nullptr;
            size_t cap = 0;
            std::string mappedBase;
            while (getline(&line, &cap, mountInfo) != -1) {
                std::string lineStr(line);
                auto separator = lineStr.find(" - ");
                if (separator == std::string::npos) {
                    continue;
                }
                std::istringstream left(lineStr.substr(0, separator));
                std::string id;
                std::string parentId;
                std::string majorMinor;
                std::string root;
                std::string mountPoint;
                if (!(left >> id >> parentId >> majorMinor >> root >> mountPoint)) {
                    continue;
                }
                if (mountPoint == "/data/storage/el2/base") {
                    mappedBase = root;
                    break;
                }
            }
            free(line);
            fclose(mountInfo);
            if (!mappedBase.empty() && mappedBase.front() == '/') {
                if (mappedBase.rfind("/app/", 0) == 0) {
                    mappedBase = "/data" + mappedBase;
                }
                constexpr size_t kPrefixLen = sizeof(kSandboxPrefix) - 1;
                return mappedBase + "/" + path.substr(kPrefixLen);
            }
        }
    }

    char resolvedPath[PATH_MAX];
    if (realpath(path.c_str(), resolvedPath) != nullptr) {
        return std::string(resolvedPath);
    }
#endif
    return path;
}

std::string BuildReportDumpPath(const std::string& dumpDir, const std::string& dumpFileName) {
    return ToHostVisiblePath(dumpDir + "/" + dumpFileName);
}
}

void alloc::AllocatedSizeTracker::Page::onPageOverflow(std::size_t allocatedBytes) noexcept {
    RuntimeAssert(allocatedBytes >= allocatedBytesLastRecorded_,
                  "A page can't overflow with less allocated bytes (%zu) than there were after the last sweep (%zu)",
                  allocatedBytes, allocatedBytesLastRecorded_);
    auto allocatedSinceLastSweep = allocatedBytes - allocatedBytesLastRecorded_;
    allocatedBytesLastRecorded_ = allocatedBytes;
    auto& heap = mm::GlobalData::Instance().allocator().impl().heap();
    heap.allocatedSizeTracker().recordDifferenceAndNotifyScheduler(static_cast<std::ptrdiff_t>(allocatedSinceLastSweep));
}

void alloc::AllocatedSizeTracker::Page::afterSweep(std::size_t allocatedBytes) noexcept {
    auto diffBytes = static_cast<std::ptrdiff_t>(allocatedBytes) - static_cast<std::ptrdiff_t>(allocatedBytesLastRecorded_);
    allocatedBytesLastRecorded_ = allocatedBytes;
    auto& heap = mm::GlobalData::Instance().allocator().impl().heap();
    heap.allocatedSizeTracker().recordDifference(diffBytes);
}

std::size_t alloc::AllocatedSizeTracker::Heap::recordDifference(std::ptrdiff_t diffBytes) noexcept {
    auto prevRecord = allocatedBytes_.fetch_add(diffBytes, std::memory_order_relaxed);
    RuntimeAssert(diffBytes >= 0 || prevRecord >= -diffBytes, "Negative overflow: %td+(%td) must be >= 0", prevRecord, diffBytes);
    auto nowAllocated = static_cast<std::size_t>(prevRecord + diffBytes);
    // Re-arm dump trigger once heap usage drops back to threshold (e.g. after GC sweep).
    if (nowAllocated <= oomThreshold_) {
        hasDumped_.store(false, std::memory_order_relaxed);
    }
    return nowAllocated;
}

void alloc::AllocatedSizeTracker::Heap::recordDifferenceAndNotifyScheduler(std::ptrdiff_t diffBytes) noexcept {
    auto nowAllocated = recordDifference(diffBytes);
    if (nowAllocated > oomThreshold_) {
        // Threshold: 1.5GB. Checked on each allocation change.
        bool expectedDumped = false;
        // Only one thread proceeds; avoids duplicate dumps / HiAppEvent when allocating in parallel.
        if (hasDumped_.compare_exchange_strong(expectedDumped, true, std::memory_order_acq_rel, std::memory_order_relaxed)) {
            // Current time for dump filename.
            std::time_t now = std::time(nullptr);
            std::tm tmBuf{};
#ifdef KONAN_OHOS
            std::tm* localTime = localtime_r(&now, &tmBuf);
            if (localTime == nullptr) {
                localTime = gmtime_r(&now, &tmBuf);
            }
#else
            std::tm* localTime = std::localtime(&now);
#endif
            if (localTime == nullptr) {
                DBG_OOM("OOM dump: localtime failed; skipping filename timestamp");
                std::memset(&tmBuf, 0, sizeof(tmBuf));
                localTime = &tmBuf;
            }

            // Dump directory for open/cleanup.
            const std::string dumpDir = "/data/storage/el2/base/haps/entry/temp";

            // Keep at most 9 old dumps; new one will make 10 total.
            CleanupOldDumpFiles(dumpDir, 9);

            std::ostringstream filenameStream;
            filenameStream << "oom_dump_" << std::put_time(localTime, "%Y%m%d_%H%M%S") << ".dump";
            const std::string dumpFileName = filenameStream.str();
            const std::string finalDumpPath = dumpDir + "/" + dumpFileName;
            const std::string reportDumpPath = BuildReportDumpPath(dumpDir, dumpFileName);

            // Generate timestamp string for event reporting
            std::ostringstream tsStream;
            tsStream << std::put_time(localTime, "%Y-%m-%d %H:%M:%S");
            std::string timestampStr = tsStream.str();

            bool dumpSuccess = false;
            int fd = open(finalDumpPath.c_str(), O_WRONLY | O_CREAT | O_TRUNC, 0666);
            if (fd >= 0) {
                // Runtime Dump API writes memory to fd (does not close fd).
                DBG_OOM("Begin to dump memory to dump file");
                dumpSuccess = Kotlin_native_runtime_Debugging_dumpMemory(nullptr, fd);
                DBG_OOM("Finish to dump memory to dump file");
                if (close(fd) != 0) {
                    DBG_OOM("Failed to close OOM dump fd, errno: %{public}d", errno);
                }

                if (dumpSuccess) {
                    DBG_OOM("Memory dump successful: %{public}s", reportDumpPath.c_str());
                } else {
                    DBG_OOM("Memory dump failed: %{public}s", reportDumpPath.c_str());
                }
            } else {
                DBG_OOM("Failed to open %{public}s for memory dump. errno: %{public}d", reportDumpPath.c_str(), errno);
            }

            ReportOomEventViaHiAppEvent(reportDumpPath.c_str(), nowAllocated, oomThreshold_, timestampStr.c_str(), dumpSuccess);
        }
    }

    if (schedulerNotificationTestHook) {
        schedulerNotificationTestHook(nowAllocated);
    }
    OnMemoryAllocation(nowAllocated);
}

void alloc::test_support::setSchedulerNotificationHook(void (*hook)(std::size_t)) noexcept {
    schedulerNotificationTestHook = hook;
}
