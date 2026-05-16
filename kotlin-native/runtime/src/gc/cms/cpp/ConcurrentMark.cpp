/*
 * Copyright 2010-2024 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "ConcurrentMark.hpp"

#include "MarkAndSweepUtils.hpp"
#include "GCStatistics.hpp"
#include "Utils.hpp"
#include "GCImpl.hpp"
#include "ThreadData.hpp"
#include "StackMap.hpp"
#include "TypeInfo.h"
#include "Memory.h"
#include "GlobalData.hpp"
#include "FpUnwind.h"
#include <iostream>
#include <sstream>
#ifdef KONAN_OHOS
#include <hilog/log.h>
// region Tencent Code
#include <hitrace/trace.h>
// endregion
#endif

using namespace kotlin;

#define DUMP_DEBUG_INFO 0
#define KOTLIN_VERIFY 1
#define ENABLE_LAZY_STACKMAP 1
namespace {

class FlushActionActivator final : public mm::ExtraSafePointActionActivator<FlushActionActivator> {};

} // namespace

void gc::mark::ConcurrentMark::ThreadData::onSuspendForGC() noexcept {}

bool gc::mark::ConcurrentMark::ThreadData::tryLockRootSet() noexcept {
    bool expected = false;
    bool locked = rootSetLocked_.compare_exchange_strong(expected, true, std::memory_order_acq_rel);
    if (locked) {
        RuntimeLogDebug(
                {kTagGC}, "Thread %" PRIuPTR " have exclusively acquired thread %" PRIuPTR "'s root set", konan::currentThreadId(),
                threadData_.threadId());
    }
    return locked;
}

void gc::mark::ConcurrentMark::ThreadData::publish() noexcept {
    threadData_.Publish();
}

void gc::mark::ConcurrentMark::ThreadData::clearMarkFlags() noexcept {
    rootSetLocked_.store(false, std::memory_order_release);
}

void gc::mark::ConcurrentMark::ThreadData::ensureFlushActionExecuted() noexcept {
    flushAction_->ensureExecuted([this] { markQueue()->forceFlush(); });
}

void gc::mark::ConcurrentMark::ThreadData::onSafePoint() noexcept {
    FlushActionActivator::doIfActive([this] { ensureFlushActionExecuted(); });
}

void gc::mark::ConcurrentMark::setupBeforeSTW(GCHandle gcHandle) {
    gcHandle_ = gcHandle;

    lockedMutatorsList_ = mm::ThreadRegistry::Instance().LockForIter();

    parallelProcessor_.construct();
}

void gc::mark::ConcurrentMark::endMarkingEpoch() {
    parallelProcessor_.destroy();
    resetMutatorFlags();
    lockedMutatorsList_ = std::nullopt;
}

void gc::mark::ConcurrentMark::markInSTW() {
    std::unique_lock markLock(markMutex_);
    ParallelProcessor::Worker mainWorker(*parallelProcessor_);
    GCLogDebug(gcHandle().getEpoch(), "Creating main (#0) mark worker");

    // create mutator mark queues
    for (auto& thread : *lockedMutatorsList_) {
        thread.gc().impl().mark_.markQueue().construct(*parallelProcessor_);
    }

    completeMutatorsRootSet(mainWorker);

    barriers::enableBarriers(gcHandle().getEpoch());
#if DUMP_DEBUG_INFO
    std::cout << "stw1 end" << std::endl;
#endif
    resumeTheWorld(gcHandle());

    // global root set must be collected after all the mutator's global data have been published
    collectRootSetGlobals<MarkTraits>(gcHandle(), mainWorker);

    // Mutator threads might release their internal batch at a pretty arbitrary moment (during a barrier execution with overflow).
    // So there are not so many reliable ways to track releases of new work.
    // The number of batches sharad inside a parallel processor may only grow,
    // we use this number to decide when to finish the mark.
    auto everSharedBatches = parallelProcessor_->batchesEverShared();
    size_t iter = 0;
    bool terminateInSTW = false;
    do {
        GCLogDebug(gcHandle().getEpoch(), "Building mark closure (attempt #%zu)", iter);
        Mark<MarkTraits>(gcHandle(), mainWorker);

        RuntimeCheck(iter <= compiler::concurrentMarkMaxIterations(), "Failed to terminate mark in STW in a single iteration");
        ++iter;
        if (iter == compiler::concurrentMarkMaxIterations()) {
            GCLogWarning(gcHandle().getEpoch(), "Finishing mark closure in STW after (%zu concurrent attempts)", iter);
            stopTheWorld(gcHandle(), "GC stop the world: concurrent mark took too long");
            terminateInSTW = true;
        }
    } while (!tryTerminateMark(everSharedBatches));

    // By this point mutator mark queues may not be populated anymore.
    // However, some threads may still try to enqueue a marked object, before they observe the barrier disablement.
    // Thus, mark queue destruction takes place only later below.

    gc::processWeaks<DefaultProcessWeaksTraits>(gcHandle(), mm::ExternalRCRefRegistry::instance());

    if (!terminateInSTW) {
        stopTheWorld(gcHandle(), "GC stop the world: prepare to sweep");
    }
#if DUMP_DEBUG_INFO
    std::cout << "stw2 start" << std::endl;
#endif

    barriers::disableBarriers();

    for (auto& thread : *lockedMutatorsList_) {
        thread.gc().impl().mark_.markQueue().destroy();
    }
    endMarkingEpoch();
}

gc::GCHandle& gc::mark::ConcurrentMark::gcHandle() {
    RuntimeAssert(gcHandle_.isValid(), "GCHandle must be initialized");
    return gcHandle_;
}

void gc::mark::ConcurrentMark::completeMutatorsRootSet(MarkTraits::MarkQueue& markQueue) {
    // workers compete for mutators to collect their root set
    for (auto& thread : *lockedMutatorsList_) {
        tryCollectRootSet(thread, markQueue);
    }
}

[[maybe_unused]] static uint64_t *GetStackMapAddress(uint64_t *fp, uint32_t *funcStartPC, mm::ThreadData& thread)
{
    // The function prologue (AArch64AsmPrinter ADRP hijack) now stores the
    // absolute address of .Lstackmap_start.<func> directly into *(fp - 2) using
    // ADRP + ADD (R_AARCH64_ADR_PREL_PG_HI21 + R_AARCH64_ADD_ABS_LO12_NC, both
    // PC-relative and link-time-resolved). This is correct regardless of how
    // many concatenated stackmap blobs end up in the merged .llvm_stackmaps
    // section (the per-module/per-blob index + offsets-table indirection used
    // previously was broken under multi-blob debug builds: indices were
    // per-module-local, so module M>0 frames indexed module 0's offsets).
    // Top 16 bits are reserved for tag bits, mask off before deref.
    uint64_t addr = *(fp - 2);
    constexpr uint64_t payloadMask = (1ULL << 48) - 1;
    return reinterpret_cast<uint64_t*>(addr & payloadMask);
}

static void CollectStackMapBaseRoot(
    mm::ThreadData& thread, uint64_t* fp,
    const uint32_t* pc, std::vector<int32_t> &baseRoots)
{
#if ENABLE_LAZY_STACKMAP
    uint32_t *funcStartPC = reinterpret_cast<uint32_t*>(*(fp - 1));
    uint64_t *stackMapAddress = GetStackMapAddress(fp, funcStartPC, thread);
    std::unordered_map<int32_t, std::vector<int32_t>> base2DerivedOffsets;
    stackMap::StackMapBuilder stackMapBuilder(reinterpret_cast<uintptr_t>(funcStartPC),
        reinterpret_cast<uintptr_t>(pc), stackMapAddress);
    stackMapBuilder.collectHeapReferenceMap(base2DerivedOffsets);
    for (auto elem : base2DerivedOffsets) {
        baseRoots.push_back(elem.first);
    }
#else // else of ENABLE_LAZY_STACKMAP
    auto& pc2CallSiteInfos = thread.gc().impl().gc().gc().stackMap().pc2CallSiteInfo();
    auto callsitInfoIt = pc2CallSiteInfos.find((uintptr_t)pc);
    if (callsitInfoIt != pc2CallSiteInfos.end()) {
        for (auto& callsite : callsitInfoIt->second) {
            baseRoots.push_back(callsite.second);
        }
    }
#endif // end of ENABLE_LAZY_STACKMAP
}

#define DUMP_UNWIND_FRAME_INFO 0

static void UnwindLog(uint64_t* fp, const uint32_t* pc)
{
#if DUMP_UNWIND_FRAME_INFO
#ifdef KONAN_OHOS
    std::stringstream ss;
    ss << "      -- unwind log fp: " << std::hex << reinterpret_cast<uintptr_t>(fp)
        << ", pc: " << reinterpret_cast<uintptr_t>(pc) << std::dec;
    OH_LOG_Print(LOG_APP, LOG_INFO, LOG_DOMAIN, "Konan_main", "%{public}s", ss.str().c_str());
#else // ~KONAN_OHOS
    std::cout << "      fp: " << std::hex << reinterpret_cast<uintptr_t>(fp)
                << ", pc: " << reinterpret_cast<uintptr_t>(pc) << std::dec << std::endl;
#endif // ~KONAN_OHOS
#endif // ~DUMP_UNWIND_FRAME_INFO
}

void gc::mark::ConcurrentMark::tryCollectRootSet(mm::ThreadData& thread, MarkTraits::MarkQueue& markQueue) {
    auto& gcData = thread.gc().impl().mark_;
    if (!gcData.tryLockRootSet()) return;

    GCLogDebug(gcHandle().getEpoch(), "Root set collection on thread %" PRIuPTR " for thread %" PRIuPTR, konan::currentThreadId(), thread.threadId());
    gcData.publish();
    collectRootSetForThread<MarkTraits>(gcHandle(), markQueue, thread);
    std::vector<FrameInfo> frameInfos = GetStackFrame(thread);
    if (frameInfos.empty()) {
        return;
    }
#if DUMP_UNWIND_FRAME_INFO
#ifdef KONAN_OHOS
    std::stringstream ss;
    ss  << "----- unwind log start scanning stack";
    OH_LOG_Print(LOG_APP, LOG_INFO, LOG_DOMAIN, "Konan_main", "%{public}s", ss.str().c_str());
#else // ~KONAN_OHOS
    std::cout << "----- start scanning stack" << std::endl;
#endif // ~KONAN_OHOS
#endif // DUMP_UNWIND_FRAME_INFO
    for (size_t i = 0; i < frameInfos.size(); i++) {
        if (frameInfos[i].fa == 0 || frameInfos[i].ip == 0) {
            continue;
        }
        uint64_t* fp = reinterpret_cast<uint64_t*>(frameInfos[i].fa);
        const uint32_t* pc = reinterpret_cast<const uint32_t*>(frameInfos[i].ip);
        UnwindLog(fp, pc);
        std::vector<int32_t> baseRoots;
        CollectStackMapBaseRoot(thread, fp, pc, baseRoots);
        for (auto& baseRootOffset : baseRoots) {
            uintptr_t address = (uintptr_t)fp + baseRootOffset;
            ObjHeader* object = (ObjHeader*)*((uint64_t*)address);

            // skip null objects
            if (!object) {
                continue;
            }
            KNStateWord *word = reinterpret_cast<KNStateWord*>(object);
            if (!(word->IsValid())) {
                continue;
            }
            [[maybe_unused]] bool result = internal::collectRoot<MarkTraits>(markQueue, object);
        }
    }
}

/** Terminates the mark loop if possible, otherwise returns `false`. */
bool gc::mark::ConcurrentMark::tryTerminateMark(std::size_t& everSharedBatches) noexcept {
    // prevent unwanted mutations (such as weak-reachable resurrection) during termination detection
    std::unique_lock markTerminationGuard(markTerminationMutex_);

    // has to happen under the termination lock guard
    flushMutatorQueues();

    // After the mutators have been forced to flush their local queues,
    // there is only on possibility for this counter to remain the same as on a previous iteration:
    // 1. Mutator local queues are empty,
    // 2. AND were empty before the flush request was made,
    // 3. AND the last attempt at completing mark closure encountered 0 new objects // FIXME this is actually redundant
    const auto nowSharedBatches = parallelProcessor_->batchesEverShared();
    if (nowSharedBatches > everSharedBatches) {
        everSharedBatches = nowSharedBatches;
        parallelProcessor_->resetForNewWork();
        return false;
    }
    RuntimeAssert(nowSharedBatches == everSharedBatches, "This number must decrease");

    barriers::switchToWeakProcessingBarriers();
    return true;
}

void gc::mark::ConcurrentMark::flushMutatorQueues() noexcept {
    for (auto& mutator : *lockedMutatorsList_) {
        mutator.gc().impl().mark_.flushAction_.construct();
    }

    {
        FlushActionActivator flushActivator{};

        // wait all mutators flushed
        while (true) {
            bool allDone = true;
            for (auto& mutator : *lockedMutatorsList_) {
                auto& markData = mutator.gc().impl().mark_;
                if (mutator.suspensionData().suspendedOrNative()) {
                    markData.ensureFlushActionExecuted();
                } else if (!markData.flushAction_->executed()) {
                    allDone = false;
                }
            }
            if (allDone) break;
            std::this_thread::yield();
        }
    }

    // It's guaranteed by the activator that no mutator thread would access somethingFlushed_ at this point.
    for (auto& mutator : *lockedMutatorsList_) {
        mutator.gc().impl().mark_.flushAction_.destroy();
    }
}

void gc::mark::ConcurrentMark::resetMutatorFlags() {
    for (auto& mut : *lockedMutatorsList_) {
        mut.gc().impl().mark_.clearMarkFlags();
    }
}

bool gc::mark::test_support::flushActionRequested() {
    return FlushActionActivator::isActive();
}
