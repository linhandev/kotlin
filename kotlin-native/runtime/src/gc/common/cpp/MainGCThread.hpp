/*
 * Copyright 2010-2025 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

#include "Allocator.hpp"
#include "GCScheduler.hpp"
#include "GCState.hpp"
#include "Utils.hpp"
#include "concurrent/UtilityThread.hpp"
#include "GlobalData.hpp"
#include "GCStatistics.hpp"
#include "MarkAndSweepUtils.hpp"
#include "StackMap.hpp"
#include "CompressedStackMap.hpp"
#include <string_view>
#include <iostream>

// Unified stackmap-format switch (replaces former ENABLE_LAZY_STACKMAP / ENABLE_COMPERSSED_STACKMAP).
// ON  (1, production) : lazy per-function compressed-bitmap stackmap; local-anchor relative offsets.
// OFF (0, comparison) : upstream-standard eager full table; absolute addresses.
// Driven by runtime/build.gradle.kts (-DENABLE_COMPRESSED_BITMAP_STACKMAP); must stay paired with the
// LLVM -enable-compressed-bitmap-stackmap codegen flag set in konan.properties.
#ifndef ENABLE_COMPRESSED_BITMAP_STACKMAP
#define ENABLE_COMPRESSED_BITMAP_STACKMAP 1
#endif
#if KONAN_LINUX || KONAN_OHOS
extern "C" uint8_t __LLVM_StackMaps;
#else
extern "C" uint8_t _LLVM_StackMaps;
#endif

#define DUMP_DEBUG_INFO 0

namespace kotlin::gc::internal {

template <typename GCTraits>
class MainGCThread : private MoveOnly {
public:
    MainGCThread(GCStateHolder& state, alloc::Allocator& allocator,
                 gcScheduler::GCScheduler& gcScheduler, typename GCTraits::Mark& mark) noexcept :
        state_(state),
        allocator_(allocator),
        gcScheduler_(gcScheduler),
        mark_(mark),
#if ENABLE_COMPRESSED_BITMAP_STACKMAP
        // ON: lazy compressed stackmap — nothing is built at startup; the marker
        // resolves each frame's records on demand during STW.
        thread_(std::string_view("Main GC thread"), [this] { body(); }) {
#else // ENABLE_COMPRESSED_BITMAP_STACKMAP
        // OFF: eager upstream-standard full table built once at startup.
        thread_(std::string_view("Main GC thread"), [this] { body(); }),
#if KONAN_LINUX || KONAN_OHOS
        stackMap_(&__LLVM_StackMaps) {
#else // KONAN_LINUX || KONAN_OHOS
        stackMap_(&_LLVM_StackMaps) {
#endif // KONAN_LINUX || KONAN_OHOS
        stackMap_.Build();
#if DUMP_DEBUG_INFO
        stackMap_.Print();
#endif
#endif // ENABLE_COMPRESSED_BITMAP_STACKMAP
            }
#if !ENABLE_COMPRESSED_BITMAP_STACKMAP
    // OFF only: expose the eager full table to the marker (ConcurrentMark uses
    // stackMap().pc2CallSiteInfo()). ON resolves records lazily, without this.
    stackMap::StackMap &stackMap() noexcept { return stackMap_; }
#endif // !ENABLE_COMPRESSED_BITMAP_STACKMAP

private:
    void body() noexcept {
        RuntimeLogInfo({ kTagGC }, "Initializing %s GC.", GCTraits::kName);
        while (true) {
            if (auto epoch = state_.waitScheduled()) {
                PerformFullGC(*epoch);
            } else {
                break;
            }
        }
        mark_.requestShutdown();
    }

    void PerformFullGC(int64_t epoch) noexcept {
#if DUMP_DEBUG_INFO
        std::cout << "gc::ConcurrentMarkAndSweep::PerformFullGC" << std::endl;
#endif
        auto mainGCLock = mm::GlobalData::Instance().gc().gcLock();

        auto gcHandle = GCHandle::create(epoch);

        mark_.setupBeforeSTW(gcHandle);

        stopTheWorld(gcHandle, "GC stop the world: mark");
#if DUMP_DEBUG_INFO
        std::cout << "stw1 start" << std::endl;
#endif

        gcScheduler_.onGCStart();

        state_.start(epoch);

        mark_.markInSTW();

        // TODO outline as mark_.isolateMarkedHeapAndFinishMark()
        // By this point all the alive heap must be marked.
        // All the mutations (incl. allocations) after this method will be subject for the next GC.
        // This should really be done by each individual thread while waiting
        for (auto& thread : kotlin::mm::ThreadRegistry::Instance().LockForIter()) {
            thread.allocator().prepareForGC();
        }
        allocator_.prepareForGC();

        if (GCTraits::kConcurrentSweep) {
            resumeTheWorld(gcHandle);
        }

        allocator_.sweep(gcHandle);
        gcScheduler_.onGCFinish(epoch, gcHandle.getKeptSizeBytes());

        if (!GCTraits::kConcurrentSweep) {
#if DUMP_DEBUG_INFO
            std::cout << "stw2 end" << std::endl;
#endif
            resumeTheWorld(gcHandle);
        }
#ifdef ENABLE_STACKMAP
        // GC live-bytes debug logging (unrelated to precise stack scanning).
        // ON path emits GCLogInfo; OFF path falls back to the baseline (no log).
        size_t liveBytes = gcHandle.getKeptSizeBytes();
        GCLogInfo(epoch, "live bytes after one gc: %zu bytes", liveBytes);
#endif
        state_.finish(epoch);
        gcHandle.finished();

        // This may start a new thread. On some pthreads implementations, this may block waiting for concurrent thread
        // destructors running. So, it must ensured that no locks are held by this point.
        // TODO: Consider having an always on sleeping finalizer thread.
        allocator_.scheduleFinalization(gcHandle);
    }

    GCStateHolder& state_;
    alloc::Allocator& allocator_;
    gcScheduler::GCScheduler& gcScheduler_;
    typename GCTraits::Mark& mark_;
    UtilityThread thread_;
#if !ENABLE_COMPRESSED_BITMAP_STACKMAP
    stackMap::StackMap stackMap_; // OFF: eager upstream-standard full table
#endif // !ENABLE_COMPRESSED_BITMAP_STACKMAP
};

} // namespace kotlin::gc::internal
