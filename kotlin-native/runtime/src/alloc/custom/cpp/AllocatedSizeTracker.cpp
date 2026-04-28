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
#include <deviceinfo.h>
#include <dlfcn.h>
#define OHOS_DUMPLISTNER_MIN_API 26
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
constexpr std::size_t K_MAX_OOM_DUMP_FILES = 10;

bool IsOomDumpFileName(const std::string& filename) {
    return filename.find("oom_dump_") == 0 && filename.size() >= K_DUMP_FILE_EXTENSION_LENGTH &&
           filename.compare(
               filename.size() - K_DUMP_FILE_EXTENSION_LENGTH,
               K_DUMP_FILE_EXTENSION_LENGTH, K_DUMP_FILE_EXTENSION) == 0;
}

bool ShouldReplaceOldestDump(
    time_t candidateMtime,
    const std::string& candidatePath,
    bool hasOldest,
    time_t oldestMtime,
    const std::string& oldestDumpFile) {
    const bool isOlder = candidateMtime < oldestMtime;
    const bool sameTimeButSmallerPath = candidateMtime == oldestMtime && candidatePath < oldestDumpFile;
    return !hasOldest || isOlder || sameTimeButSmallerPath;
}

#ifdef KONAN_OHOS
#define KN_STRINGIFY_(x) #x
#define KN_STRINGIFY(x) KN_STRINGIFY_(x)

#ifndef KOTLIN_NATIVE_HIAPPEVENT_FW_VERSION
#define KOTLIN_NATIVE_HIAPPEVENT_FW_VERSION unknown
#endif

using OHHiAppEventReportFrameworkMemAnomalyFn =
    int (*)(OH_HiAppEvent_FrameworkType type, const char* value, const char* description);

static OHHiAppEventReportFrameworkMemAnomalyFn resolveOHHiAppEventReportFrameworkMemAnomaly() {
    static OHHiAppEventReportFrameworkMemAnomalyFn fn =
        reinterpret_cast<OHHiAppEventReportFrameworkMemAnomalyFn>(
            dlsym(RTLD_DEFAULT, "OH_HiAppEvent_ReportFrameworkMemAnomaly"));
    return fn;
}

static void ReportOomEventViaHiAppEvent(
    const char* dumpPath,
    std::size_t memUsage,
    std::size_t threshold,
    const char* timestamp) {
    if (OH_GetSdkApiVersion() < OHOS_DUMPLISTNER_MIN_API) {
        return;
    }

    std::ostringstream desc;
    desc << "Kotlin/Native heap over OOM threshold; dump_path=" << dumpPath << "; memory_usage=" << memUsage
         << "; oom_threshold=" << threshold << "; timestamp=" << timestamp;
    std::string descriptionStr = desc.str();
    OHHiAppEventReportFrameworkMemAnomalyFn reportFrameworkMemAnomaly = resolveOHHiAppEventReportFrameworkMemAnomaly();
    if (reportFrameworkMemAnomaly == nullptr) {
        DBG_OOM("HiAppEvent: ReportFrameworkMemAnomaly symbol not found");
        return;
    }
    int reportResult = reportFrameworkMemAnomaly(
        OH_KMP_KOTLIN, KN_STRINGIFY(KOTLIN_NATIVE_HIAPPEVENT_FW_VERSION), descriptionStr.c_str());
    DBG_OOM("HiAppEvent: ReportFrameworkMemAnomaly finished. result=%{public}d", reportResult);
}
#else
static void ReportOomEventViaHiAppEvent(
    const char*,
    std::size_t,
    std::size_t,
    const char*) {
    // No-op on non-OHOS platforms
}
#endif

// Delete one oldest dump file when total count reaches maxFiles.
void CleanupOldDumpFiles(
    const std::string& directory,
    int maxFiles) {
    DIR* dir = opendir(directory.c_str());
    if (dir == nullptr) {
        DBG_OOM("Failed to open directory %{public}s for reading dump files", directory.c_str());
        return;
    }

    int dumpFileCount = 0;
    std::string oldestDumpFile;
    time_t oldestMtime = 0;
    bool hasOldest = false;

    struct dirent* entry;
    while ((entry = readdir(dir)) != nullptr) {
        std::string filename = entry->d_name;
        // Match oom_dump_YYYYMMDD_HHMMSS.dump
        if (!IsOomDumpFileName(filename)) {
            continue;
        }

        std::string fullPath = directory + "/" + filename;
        struct stat st;
        if (stat(fullPath.c_str(), &st) != 0) {
            continue;
        }
        ++dumpFileCount;

        if (ShouldReplaceOldestDump(st.st_mtime, fullPath, hasOldest, oldestMtime, oldestDumpFile)) {
            oldestMtime = st.st_mtime;
            oldestDumpFile = fullPath;
            hasOldest = true;
        }
    }
    closedir(dir);

    if (dumpFileCount < maxFiles) {
        return;
    }

    if (!hasOldest) {
        DBG_OOM("No valid dump file found to delete in %{public}s", directory.c_str());
        return;
    }

    if (unlink(oldestDumpFile.c_str()) == 0) {
        DBG_OOM("Deleted oldest dump file: %{public}s", oldestDumpFile.c_str());
    } else {
        DBG_OOM("Failed to delete oldest dump file: %{public}s, errno: %{public}d", oldestDumpFile.c_str(), errno);
    }
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

bool alloc::AllocatedSizeTracker::Heap::ShouldDumpAndMark(std::size_t nowAllocated) noexcept {
    if (nowAllocated > oomThreshold_) {
        // Threshold: 1.5GB. Checked on each allocation change.
        bool expectedDumped = false;
        // Only one thread proceeds; avoids duplicate dumps / HiAppEvent when allocating in parallel.
        if (hasDumped_.compare_exchange_strong(
            expectedDumped, true, std::memory_order_acq_rel, std::memory_order_relaxed)) {
            return true;
        }
    }
    return false;
}

std::tm* alloc::AllocatedSizeTracker::Heap::ResolveLocalTimeOrFallback(std::time_t now, std::tm& tmBuf) noexcept {
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
    return localTime;
}

void alloc::AllocatedSizeTracker::Heap::BuildDumpMetadata(
    const std::tm* localTime, const std::string& dumpDir, std::string& finalDumpPath,
    std::string& timestampStr) noexcept {
    std::ostringstream filenameStream;
    filenameStream << "oom_dump_" << std::put_time(localTime, "%Y%m%d_%H%M%S") << ".dump";
    const std::string dumpFileName = filenameStream.str();
    finalDumpPath = dumpDir + "/" + dumpFileName;

    std::ostringstream tsStream;
    tsStream << std::put_time(localTime, "%Y-%m-%d %H:%M:%S");
    timestampStr = tsStream.str();
}

void alloc::AllocatedSizeTracker::Heap::DumpMemoryToFile(
    const std::string& finalDumpPath) noexcept {
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
            DBG_OOM("Memory dump successful (sandbox path): %{public}s", finalDumpPath.c_str());
        } else {
            DBG_OOM("Memory dump failed (sandbox path): %{public}s", finalDumpPath.c_str());
        }
    } else {
        DBG_OOM("Failed to open %{public}s for memory dump. errno: %{public}d", finalDumpPath.c_str(), errno);
    }
}

void alloc::AllocatedSizeTracker::Heap::MaybeDumpAndReportOom(std::size_t nowAllocated) noexcept {
    if (!ShouldDumpAndMark(nowAllocated)) {
        return;
    }

    std::time_t now = std::time(nullptr);
    std::tm tmBuf{};
    std::tm* localTime = ResolveLocalTimeOrFallback(now, tmBuf);
    const std::string dumpDir = "/data/storage/el2/base/haps/entry/temp";
    CleanupOldDumpFiles(dumpDir, K_MAX_OOM_DUMP_FILES);

    std::string finalDumpPath;
    std::string timestampStr;
    BuildDumpMetadata(localTime, dumpDir, finalDumpPath, timestampStr);
    ReportOomEventViaHiAppEvent(finalDumpPath.c_str(), nowAllocated, oomThreshold_, timestampStr.c_str());
    DumpMemoryToFile(finalDumpPath);
}

void alloc::AllocatedSizeTracker::Heap::NotifyScheduler(std::size_t nowAllocated) noexcept {
    if (schedulerNotificationTestHook) {
        schedulerNotificationTestHook(nowAllocated);
    }
    OnMemoryAllocation(nowAllocated);
}

void alloc::AllocatedSizeTracker::Heap::recordDifferenceAndNotifyScheduler(std::ptrdiff_t diffBytes) noexcept {
    auto nowAllocated = recordDifference(diffBytes);
    MaybeDumpAndReportOom(nowAllocated);
    NotifyScheduler(nowAllocated);
}

void alloc::test_support::setSchedulerNotificationHook(void (*hook)(std::size_t)) noexcept {
    schedulerNotificationTestHook = hook;
}
