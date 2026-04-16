/*
 * Copyright 2022-2024 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

#include <atomic>
#include <ctime>
#include <string>

namespace kotlin::alloc {

struct AllocatedSizeTracker {
    class Page {
    public:
        void onPageOverflow(std::size_t allocatedBytes) noexcept;
        void afterSweep(std::size_t allocatedBytes) noexcept;

    private:
        std::size_t allocatedBytesLastRecorded_ = 0;
    };

    class Heap {
    public:
        /** Returns the tracker value after the update. */
        std::size_t recordDifference(std::ptrdiff_t diffBytes) noexcept;
        void recordDifferenceAndNotifyScheduler(std::ptrdiff_t diffBytes) noexcept;
    private:
        bool ShouldDumpAndMark(std::size_t nowAllocated) noexcept;
        std::tm* ResolveLocalTimeOrFallback(std::time_t now, std::tm& tmBuf) noexcept;
        void BuildDumpMetadata(
                const std::tm* localTime, const std::string& dumpDir, std::string& finalDumpPath,
                std::string& reportDumpPath, std::string& timestampStr) noexcept;
        void DumpMemoryToFile(const std::string& finalDumpPath, const std::string& reportDumpPath) noexcept;
        void MaybeDumpAndReportOom(std::size_t nowAllocated) noexcept;
        void NotifyScheduler(std::size_t nowAllocated) noexcept;
        std::atomic<std::ptrdiff_t> allocatedBytes_ = 0;
        std::size_t oomThreshold_ = 1536 * 1024 * 1024; // 1.5GB
        std::atomic<bool> hasDumped_{false};
    };
};

namespace test_support {
void setSchedulerNotificationHook(void (*hook)(std::size_t)) noexcept;
}

}
