/*
 * Copyright 2010-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

#include <algorithm>
#include <atomic>
#include <cmath>
#include <cstddef>
#include <cstdint>

#include "GCSchedulerConfig.hpp"
#include "Logging.hpp"

namespace kotlin::gcScheduler::internal {
// 1024 is KB
constexpr uint32_t BYTES_PER_MB = 1024 * 1024;

class HeapGrowthController {
public:
    enum class MemoryBoundary {
        // Memory usage is low.
        kNone,
        // Memory usage is high, GC should be triggered.
        kTrigger,
        // Memory usage is critical, GC is running behind the mutators. Mutators should pause.
        kTarget,
    };

    explicit HeapGrowthController(GCSchedulerConfig& config) noexcept : config_(config) {}

    // Can be called by any thread.
    MemoryBoundary boundaryForHeapSize(size_t totalAllocatedBytes) noexcept {
        const size_t target = static_cast<size_t>(
            config_.targetHeapBytes.load(std::memory_order_acquire));
        const size_t trigger = static_cast<size_t>(
            target * config_.heapTriggerCoefficient.load(std::memory_order_relaxed));
        RuntimeLogDebug({logging::Tag::kGCScheduler}, "Total allocated %zu bytes", totalAllocatedBytes);
        if (totalAllocatedBytes >= target) {
            return config_.mutatorAssists() ? MemoryBoundary::kTarget : MemoryBoundary::kTrigger;
        } else if (totalAllocatedBytes >= trigger) {
            return MemoryBoundary::kTrigger;
        } else {
            return MemoryBoundary::kNone;
        }
    }

    // Called by the GC thread.
    void updateBoundaries(size_t aliveBytes) noexcept {
        if (config_.autoTune.load()) {
            double oldTargetHeapSize = config_.targetHeapBytes.load(std::memory_order_relaxed);
            double threshold1 = static_cast<double>(aliveBytes) / config_.targetHeapUtilization;
            double targetHeapBytes = (threshold1 + oldTargetHeapSize) / 2;
            RuntimeLogInfo({kTagGC}, "Epoch: targetHeapBytes_ =: %d %d %d %d",
                           (uint32_t)aliveBytes/BYTES_PER_MB,
                           (uint32_t)oldTargetHeapSize/BYTES_PER_MB,
                           (uint32_t)threshold1/BYTES_PER_MB,
                           (uint32_t)targetHeapBytes/BYTES_PER_MB);

            if (!std::isfinite(targetHeapBytes)) {
                // This shouldn't happen in practice: targetHeapUtilization is in (0, 1]. But in case it does, don't touch anything.
                return;
            }
            double minHeapBytes = static_cast<double>(config_.minHeapBytes.load(std::memory_order_relaxed));
            double maxHeapBytes = static_cast<double>(config_.maxHeapBytes.load(std::memory_order_relaxed));
            targetHeapBytes = std::min(std::max(targetHeapBytes, minHeapBytes), maxHeapBytes);
            config_.targetHeapBytes.store(
                static_cast<int64_t>(targetHeapBytes), std::memory_order_release);
        }
        RuntimeLogInfo({logging::Tag::kGCScheduler},
                       "Updated heap boundaries: alive %zu, target %zu, trigger %zu", aliveBytes, targetHeapBytes(), triggerHeapBytes());
    }

    size_t targetHeapBytes() const noexcept {
        return static_cast<size_t>(config_.targetHeapBytes.load(std::memory_order_relaxed));
    }
    size_t triggerHeapBytes() const noexcept {
        return static_cast<size_t>(
            targetHeapBytes() * config_.heapTriggerCoefficient.load(std::memory_order_relaxed));
    }

private:
    GCSchedulerConfig& config_;
};

} // namespace kotlin::gcScheduler::internal
