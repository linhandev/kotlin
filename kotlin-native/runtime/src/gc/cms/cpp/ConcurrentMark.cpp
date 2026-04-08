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
#include "VerifyKotlinStack.hpp"

using namespace kotlin;

#if KONAN_LINUX || KONAN_OHOS
extern "C" uint8_t __LLVM_StackMaps;
extern "C" uint8_t __LLVM_StackMap_Offsets;
static uintptr_t stackMapsSection = reinterpret_cast<uintptr_t>(&__LLVM_StackMaps);
static uintptr_t stackMapOffsetsSection = reinterpret_cast<uintptr_t>(&__LLVM_StackMap_Offsets);
#else
extern "C" uint8_t _LLVM_StackMaps;
extern "C" uint8_t _LLVM_StackMap_Offsets;
static uintptr_t stackMapsSection = reinterpret_cast<uintptr_t>(&_LLVM_StackMaps);
static uintptr_t stackMapOffsetsSection = reinterpret_cast<uintptr_t>(&_LLVM_StackMap_Offsets);
#endif

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
    uint64_t stackMapOffsetIndex = *(fp - 2);

#ifdef KOTLIN_VERIFY
    if (!kotlin::mm::VerifyKotlinStack::IsKotlinFrameTag(fp)) {
        RuntimeLogInfo({kTagGC}, "DFX error: unwind is not kotlin frame, stackMapOffsetIndex %llu, thread %" PRIuPTR ", aborting\n",
            (unsigned long long)stackMapOffsetIndex, thread.threadId());
        auto& currentKotlinFrame = thread.getLastKotlinFrame();
        for (size_t i = 0; i < currentKotlinFrame.fpStack_.size(); i++) {
            RuntimeLogInfo({kTagGC}, "[KotlinFrame] fpStack_[%zu]: %p\n", i, currentKotlinFrame.fpStack_[i]);
        }
        thread.printLastKotlinFrameLog();
        for (size_t i = 0; i < currentKotlinFrame.pcStack_.size(); i++) {
            RuntimeLogInfo({kTagGC}, "[KotlinFrame] pcStack_[%zu]: %p\n", i, currentKotlinFrame.pcStack_[i]);
        }
        for (size_t i = 0; i < currentKotlinFrame.fpStack_.size(); i++) {
            uint64_t* fp_i = currentKotlinFrame.fpStack_[i];
            uint32_t* pc_i = (uint32_t*)*(fp_i + 1);
            RuntimeLogInfo({kTagGC}, "[KotlinFrame] from fpStack_[%zu] get pc: %p\n", i, pc_i);
        }
        abort();
    }
#endif
    constexpr uint64_t PAYLOAD_MASK = (1ULL << 48) - 1;
    stackMapOffsetIndex &= PAYLOAD_MASK;

    uint64_t actualOffset = *(reinterpret_cast<uint32_t*>(stackMapOffsetsSection) + stackMapOffsetIndex);
    uint64_t *stackMapAddress = reinterpret_cast<uint64_t*>(stackMapsSection + actualOffset);

    return stackMapAddress;
}

bool ShouldMarkEntryCaller(FrameKind kind)
{
    return IsKotlinFrame(kind);
}

static void CollectStackMapBaseRoot(mm::ThreadData& thread, uint64_t* fp, uint32_t* pc, std::vector<int32_t> &baseRoots)
{
#if ENABLE_LAZY_STACKMAP
    uint32_t *funcStartPC = (uint32_t *)*(fp - 1);
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

template <typename MarkTraits>
ALWAYS_INLINE void ProcessStackFrame(typename MarkTraits::MarkQueue& markQueue, mm::ThreadData& thread, uint64_t* fp, uint32_t* pc) {
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

        [[maybe_unused]] bool result = gc::internal::collectRoot<MarkTraits>(markQueue, object);
#if DUMP_DEBUG_INFO
        if (result) {
            std::cout << "    Stackmap collecting stack root: 0x" << std::hex << (uintptr_t)(object) << std::dec << "\n";
        } else {
            std::cout << "    Stackmap skipping stack root: 0x" << std::hex << (uintptr_t)(object) << std::dec << "\n";
        }
#endif
    }
}

void gc::mark::ConcurrentMark::tryCollectRootSet(mm::ThreadData& thread, MarkTraits::MarkQueue& markQueue) {
    auto& gcData = thread.gc().impl().mark_;
    if (!gcData.tryLockRootSet()) return;

    GCLogDebug(gcHandle().getEpoch(), "Root set collection on thread %" PRIuPTR " for thread %" PRIuPTR, konan::currentThreadId(), thread.threadId());
    gcData.publish();
    collectRootSetForThread<MarkTraits>(gcHandle(), markQueue, thread);

    auto& currentKotlinFrame = thread.getLastKotlinFrame();
    const auto& frameKinds = currentKotlinFrame.kindStack_;
    const size_t stackSize = currentKotlinFrame.fpStack_.size();

    for (int i = stackSize - 1; i >= 0;) {
        FrameKind topKind = static_cast<FrameKind>(frameKinds[i]);

        if (!IsExitFrame(topKind)) {
            i--;
            continue;
        }

        int entryIdx = i - 1;
        while (entryIdx >= 0 && !IsEntryFrame(static_cast<FrameKind>(frameKinds[entryIdx]))) {
            entryIdx--;
        }

        if (entryIdx < 0) {
            break;
        }

        FrameKind entryKind = static_cast<FrameKind>(frameKinds[entryIdx]);

        uint64_t* fp = currentKotlinFrame.fpStack_[i];
        uint32_t* pc = currentKotlinFrame.pcStack_[i];
        uint64_t* stopFp = currentKotlinFrame.fpStack_[entryIdx];

        if (!IsKotlinFrame(topKind)) {
            pc = (uint32_t*)*(fp + 1);
            fp = (uint64_t*)*fp;
        }

        while (fp != stopFp) {
            if (kotlin::mm::VerifyKotlinStack::IsKotlinFrameTag(fp)) {
                ProcessStackFrame<MarkTraits>(markQueue, thread, fp, pc);
            }
            if (fp == nullptr) {
                abort();
            }
            pc = (uint32_t*)*(fp + 1);
            fp = (uint64_t*)*fp;
        }

        if (ShouldMarkEntryCaller(entryKind)) {
            ProcessStackFrame<MarkTraits>(markQueue, thread, fp, pc);
        }

        i = entryIdx - 1;
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
