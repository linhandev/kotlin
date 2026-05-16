/*
 * Copyright (c) 2025 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <fstream>
#include "KNRootVisitor.hpp"
#include "RootSet.hpp"
#include "Logging.hpp"
#include "gc/common/cpp/CompressedStackMap.hpp"
#include "gc/common/cpp/GC.hpp"
#include "FpUnwind.h"

// LLVM 19.1.4 does not emit the `_LLVM_StackMap_Offsets` index symbol that the
// "lazy" stackmap-lookup path expects (which reads a per-function index from
// fp-2 and indexes into the offsets array). Force the eager path, which scans
// `_LLVM_StackMaps` once at first use to build a PC->callsite map and depends
// only on the standard `_LLVM_StackMaps` section. `constexpr false` makes the
// dead lazy branch (including `GetStackMapAddress`) statically eliminated, so
// the unresolved `_LLVM_StackMap_Offsets` reference disappears at link time.
static bool IsEnableLazyStackMap() {
    return true;
}
static const bool enableLazyStackMap = IsEnableLazyStackMap();
static constexpr bool enableLogStackMap = false; // used to debug, here we just hardcode it to false

namespace kotlin {

// Mirrors gc/cms/cpp/ConcurrentMark.cpp::GetStackMapAddress: the AArch64
// prologue stashes the absolute address of .Lstackmap_start.<func> at *(fp-2)
// via ADRP+ADD (link-time-resolved). Top 16 bits are reserved for tag bits.
static uint64_t* GetStackMapAddress(uint64_t* fp, uint32_t* funcStartPC) {
    uint64_t addr = *(fp - 2);
    constexpr uint64_t payloadMask = (1ULL << 48) - 1;
    return reinterpret_cast<uint64_t*>(addr & payloadMask);
}

struct StackMapRecord {
    stackMap::StackMapTable stackMapTable;
    stackMap::RegTable regTable;
    stackMap::SlotTable slotTable;
    stackMap::DerivedPtrTable derivedTable;
};

StackMapRecord ResolveStackMapEntry(std::ostream& logStackMapFile, uint8_t* stackmapStart);

#define LOG_STACK_MAP(addr) enableLogStackMap&& logStackMapFile << (void*)(addr) << ": "

static std::ostream& operator<<(std::ostream& s, const stackMap::BitsManager& b) {
    s << std::hex << "BitsManager(addr=" << b.getAddr() << ", bitPos=0x" << *(uint32_t*)((uint8_t**)(&b) + 1) << ")";
    return s;
}

StackMapRecord ResolveStackMapEntry(std::ostream& logStackMapFile, uint8_t* stackmapStart) {
    using namespace stackMap;

    // inline the `CompressedStackMapHead::GetStackMapHead`
    StackMapHeaderVarInt stacksizeVarInt((uint8_t*)stackmapStart, 0);
    uint32_t stackSize = stacksizeVarInt.GetStacksize();
    LOG_STACK_MAP(stackmapStart) << "\tstacksizeVarInt={value: " << stacksizeVarInt.GetNextTable() << "stackSize: " << std::dec << stackSize
                                 << "}" << std::endl;

    StackMapHeaderVarInt compressedFormatVarInt(stacksizeVarInt.GetNextTable());
    uint32_t slotFormat = compressedFormatVarInt.GetStacksize();
    LOG_STACK_MAP(stacksizeVarInt.GetNextTable().getAddr())
            << std::hex << "\tcompressedFormatVarInt={prologue: " << compressedFormatVarInt.GetNextTable() << "format: 0x" << slotFormat
            << "}" << std::endl;

    /**
     * `CompressedStackMapHead::CompressedStackMapHead` -> `PrologueVarInt::ResolvePrologue`
     * inline the `PrologueVarInt::ResolvePrologue`
     */
    BitsManager prologue = compressedFormatVarInt.GetNextTable();
    auto [bitMap, bitLen] = VarInt(prologue).GetValue();
    LOG_STACK_MAP(prologue.getAddr()) << std::hex << "\tcalleeSavedRegisterBitMap=0x" << bitMap << " size=0x" << __builtin_popcount(bitMap)
                                      << std::endl;

    BitsManager offsetBitManager = prologue.GetNext(bitLen);
    for (uint32_t i = 0, size = __builtin_popcount(bitMap); i < size; ++i) {
        auto [value, bits] = VarInt(offsetBitManager).GetValue();
        LOG_STACK_MAP(offsetBitManager.getAddr())
                << std::hex << offsetBitManager << "\tcalleeSavedRegister FP offset=0x" << value << " nextBits=0x" << bits << std::endl;
        offsetBitManager = offsetBitManager.GetNext(bits);
    }
    BitsManager nextTable = offsetBitManager;
    // inline end

    StackMapTable stackMapTable(nextTable);
    RegTable regTable(stackMapTable.GetNextTable());
    SlotTable slotTable(regTable.GetNextTable(), slotFormat);
    DerivedPtrTable derivedTable(slotTable.GetNextTable(), stackMapTable.GetRegBitsLen(), stackMapTable.GetSlotBitsLen());
    LOG_STACK_MAP(nextTable.getAddr()) << std::hex << "\tstackMapTable=" << nextTable.getAddr() << " regTable=0x"
                                       << stackMapTable.GetNextTable().getAddr() << " slotTable=0x" << regTable.GetNextTable().getAddr()
                                       << " derivedTable=0x" << slotTable.GetNextTable().getAddr() << std::endl;

    return StackMapRecord{stackMapTable, regTable, slotTable, derivedTable};
}

/**
 * 问题：1. PrologueRegisterClosure只保存到了临时变量里，全局的StackMapBuilder::build并没有将其保存起来里
 *         也没有反应到collectHeapReferenceMap返回数据集里
 *      2. 全局的StackMapBuilder::build没有保存DerivedPointer与base指针的关系
 * 解决：重写stackmap的生成逻辑如下，以保存如上两点信息
 */
static NO_INLINE auto GenerateAllStackMaps() {
    // see kotlin::StackMapBuilder::StackMap::build
    const uint8_t* const stackMapBase = LLVM_STACKMAP_SYMBOL;
    std::unordered_map<uintptr_t, std::map<int32_t, std::vector<int32_t>>> pc2CallSiteInfo;

    std::ofstream logStackMapFile;
    if (enableLogStackMap) {
        logStackMapFile.open("stack_map.log", std::ios::out);
    }
    using namespace stackMap;

    LOG_STACK_MAP(stackMapBase) << "FunctionCount=" << *(uint64_t*)stackMapBase << std::endl;
    for (uint64_t stackMapIndex = 0, functionCount = *(uint64_t*)stackMapBase, stackMapAddr = (uint64_t)(stackMapBase + 8);
         stackMapIndex < functionCount; ++stackMapIndex, stackMapAddr = stackMapAddr + *(uint32_t*)(stackMapAddr + 8)) {
        // inilne the `CompressedStackMapHead::GetStackMapHead`
        uintptr_t stackmapStart = stackMapAddr;
        // funcAddrOffset is stored as (function_symbol - .Lstackmap_start.<func>),
        // a PREL64 relocation against the function symbol. The base of the diff is
        // this entry's own start label, NOT the global __LLVM_StackMaps symbol —
        // the old scheme (stackMapBase + offset) gives wrong addresses under
        // multi-blob layouts (debug builds with cached klibs). See the matching
        // comment in CompressedStackMap.hpp::CompressedStackMapHead::GetStackMapHead.
        uint64_t funcAddress = static_cast<uint64_t>(
            *reinterpret_cast<int64_t*>(stackmapStart) +
            static_cast<int64_t>(stackmapStart));
        LOG_STACK_MAP(stackmapStart) << stackMapIndex << ": Function=" << (void*)funcAddress << std::endl;

        stackmapStart += 8; // skip funcAddress
        LOG_STACK_MAP(stackmapStart) << std::hex << "\tstackMapSize=0x" << *(const uint32_t*)stackmapStart << std::endl;

        stackmapStart += 4; // skip stackMapSize

        // ResolveStackMapEntry similar to `CompressedStackMapHead::GetStackMapHead`
        auto stackMapRecord = ResolveStackMapEntry(logStackMapFile, (uint8_t*)stackmapStart);
        StackMapTable& stackMapTable = stackMapRecord.stackMapTable;
        RegTable& regTable = stackMapRecord.regTable;
        SlotTable& slotTable = stackMapRecord.slotTable;
        DerivedPtrTable& derivedTable = stackMapRecord.derivedTable;

        // inline the CompressedStackMapHead::CollectAllStackMapEntry
        for (auto iter = stackMapTable.IdxSetBegin(), end = stackMapTable.IdxSetEnd(); iter != end; ++iter) {
            auto idxSet = *iter;
            uintptr_t curPC = funcAddress + idxSet.pc;
            LOG_STACK_MAP(0) << std::hex << "\t\tcallsite=0x" << curPC << std::endl;

            auto& callSite = pc2CallSiteInfo[curPC];
            CompressedStackMapEntry entry(idxSet, regTable, slotTable, derivedTable);
            entry.VisitBaseAndDerivedSlotOffsets(
                    [&callSite, &logStackMapFile](int32_t base) {
                        size_t size = callSite[base].size(); // First save the base, size is zero.
                        LOG_STACK_MAP(0) << std::dec << "\t\t\tbaseOffset=" << base << ", derivedPointerCount=" << size << std::endl;
                    },
                    [&callSite, &logStackMapFile](int32_t base, int32_t derived) {
                        callSite[base].push_back(derived);
                        LOG_STACK_MAP(0) << std::dec << "\t\t\tbaseOffset=" << base << ", derivedPointerOffset=" << derived << std::endl;
                    });
            if (callSite.empty()) {
                pc2CallSiteInfo.erase(curPC);
            }
        }
    }
    LOG_STACK_MAP(0) << std::dec << "Total valid CallSite Count=" << pc2CallSiteInfo.size() << std::endl;

    return pc2CallSiteInfo;
}
#undef LOG_STACK_MAP

std::pair<void*, void*> StackMapHelper::GetStackMapInfo() {
    uint32_t* funcStartPC = (uint32_t*)*((uintptr_t*)currentFP - 1);
    uint64_t* stackMapAddress = kotlin::GetStackMapAddress((uint64_t*)currentFP, funcStartPC);
    return {funcStartPC, stackMapAddress};
}

void StackMapHelper::collectStackMapBaseRoot() {
    if (enableLazyStackMap) {
        RuntimeLogDebug({kTagGC}, "enableLazyStackMap: currentFP=%p, currentPC=%p", currentFP, currentPC);
        // Mirror gc/cms/cpp/ConcurrentMark.cpp's lazy path: use the shared
        // stackMap::StackMapBuilder API (which internally calls
        // CompressedStackMapHead::GetStackMapHead, applying the per-entry-base
        // funcAddress fix required for multi-blob layouts) to resolve this
        // callsite's base→derived[] map, then funnel through the same
        // resolveBase2DerivedOffset helper the eager path uses.
        // Defensive: skip frames whose fp/pc aren't plausible. GetStackFrame
        // can return entries where lastFrame got corrupted (e.g. between two
        // GC.collect() callsites in the same function) so the frame pointer
        // ends up null, tiny, or inside the binary's code/data segment rather
        // than the thread stack. Feeding such a frame into GetStackMapInfo
        // would dereference *(fp-2) and crash (VarInt::GetValue reading 0xc),
        // or — worse — silently pass a stack-slot offset to the visitor and
        // write to a code-segment address.
        auto fpAddr = reinterpret_cast<uintptr_t>(currentFP);
        if (fpAddr == 0 || fpAddr < 0x10000) {
            return;
        }
        // Reject frame pointers that land inside the binary's mapped code/data
        // (real stack pointers on macOS arm64 are far above KEXE_ADDR_END_).
        if (fpAddr >= KEXE_ADDR_START_ && fpAddr < KEXE_ADDR_END_) {
            return;
        }
        const auto stackMapInfo = GetStackMapInfo();
        // The prologue tag word at *(fp-2) must be a real .Lstackmap_start.<func>
        // address. If it's null/tiny, the frame isn't a Kotlin frame (or its
        // prologue hasn't run yet). Skip rather than feed a near-null pointer
        // into VarInt::GetValue.
        auto stackmapAddr = reinterpret_cast<uintptr_t>(stackMapInfo.second);
        if (stackmapAddr == 0 || stackmapAddr < 0x10000) {
            return;
        }
        std::unordered_map<int32_t, std::vector<int32_t>> base2DerivedOffsets;
        stackMap::StackMapBuilder builder(
                reinterpret_cast<uintptr_t>(stackMapInfo.first),
                reinterpret_cast<uintptr_t>(currentPC),
                reinterpret_cast<uint64_t*>(stackMapInfo.second));
        builder.collectHeapReferenceMap(base2DerivedOffsets);
        for (auto& entry : base2DerivedOffsets) {
            resolveBase2DerivedOffset(entry);
        }
    } else {
        static auto pc2CallSiteInfos = GenerateAllStackMaps();

        auto callsitInfoIt = pc2CallSiteInfos.find((uintptr_t)currentPC);
        if (callsitInfoIt != pc2CallSiteInfos.end()) {
            for (auto& callsite : callsitInfoIt->second) {
                resolveBase2DerivedOffset(callsite);
            }
        }
    }
}

void StackMapHelper::handleDerivedPointer(uintptr_t* address, ObjHeader* base, ptrdiff_t offset) {
    if (offset == 0) {
        collectRoots((ObjHeader**)address);
        return;
    }
    if (skipHandleDerivedPointer()) {
        return;
    }
    RuntimeAssert(base != nullptr, "should skip null before handle derived");
    uintptr_t pointer = *address;

    RuntimeLogDebug({kTagGC}, "handleDerivedPointer: %p@%p derived from %p(+%ld)", address, (void*)pointer, base, offset);
    visitDerived(visitorClosure, address, base, offset);
}

void StackMapHelper::collectRoots(ObjHeader** address) {
    ObjHeader* object = *address;

    if (object == nullptr) {
        RuntimeLogDebug({kTagGC}, "record is nullptr %p@%p", address, object);
        return;
    }
    bool valid = common::IsHeapAddress(object);
    RuntimeLogDebug({kTagGC}, "mutator root: %p@%p isHeapAddr=%s", address, object, valid ? "true" : "false");
    if (!valid) {
        return;
    }
    visitRoot(visitorClosure, address);
}

void StackMapHelper::resolveBase2DerivedOffset(const std::pair<const int32_t, std::vector<int32_t>>& pair) {
    int32_t baseRootOffset = pair.first;
    ObjHeader** address = (ObjHeader**)((uintptr_t)currentFP + baseRootOffset);
    ObjHeader* object = *address; // snapshot base object
    RuntimeLogDebug(
            {kTagGC}, "visit stackmap record={callsite: %p, baseOffset: %d derivedPointerCount: %ld} %p@%p", currentPC, baseRootOffset,
            pair.second.size(), address, object);
    if (object == nullptr) {
        return;
    }
    collectRoots(address);
    for (auto& offset : pair.second) {
        uintptr_t* derivedPointerRef = (uintptr_t*)((uintptr_t)currentFP + offset);
        handleDerivedPointer(derivedPointerRef, object, *derivedPointerRef - (uintptr_t)object);
    }
}

void StackMapHelper::tryCollectRootSet() {
    for (auto value : mm::ThreadRootSet(currentThread)) {
        collectRoots(&value.object);
    }

    std::vector<FrameInfo> frameInfos = GetStackFrame(currentThread);
    for (size_t i = 0; i < frameInfos.size(); i++) {
        currentFP = reinterpret_cast<void*>(frameInfos[i].fa);
        currentPC = reinterpret_cast<void*>(const_cast<uint32_t*>(frameInfos[i].ip));
        RuntimeLogDebug(
                {kTagGC}, "currentFP=%p, currentPC=%p checkMagicNum=%p", currentFP, currentPC,
                (*(((uint64_t*)currentFP) - 2)) >> 48);
        collectStackMapBaseRoot();
    }

}

void StackMapHelper::traverseBaseRoots(const common::RefFieldVisitor* visitor) {
    visitorClosure = const_cast<common::RefFieldVisitor*>(visitor);
    visitRoot = [](void* closure, ObjHeader** address) {
        auto refVisitor = reinterpret_cast<const common::RefFieldVisitor*>(closure);
        (*refVisitor)(*reinterpret_cast<common::RefField<>*>(address));
    };
    visitDerived = nullptr;
    tryCollectRootSet();
}

void StackMapHelper::traverseBaseAndDerived(RootVisitor v1, DerivedPtrVisitor v2, void* colsure) {
    visitorClosure = colsure;
    visitRoot = v1;
    visitDerived = v2;
    tryCollectRootSet();
}

} // namespace kotlin

namespace common {

void KNRootsVisitor::VisitConcurrentRoots(const RefFieldVisitor& visitor) {
    TraverseGlobalRoots([&visitor](ObjHeader*& object) {
        RuntimeLogDebug({kotlin::kTagGC}, "global root: %p@%p isHeapAddr=%s", &object, object, IsHeapAddress(object) ? "true" : "false");
        if (!IsHeapAddress(object)) {
            // a global ref but is not a heap object.
            return;
        }
        visitor(reinterpret_cast<RefField<>&>(object));
    });
}

void KNRootsVisitor::VisitMutatorRoots(const RefFieldVisitor& visitor, ThreadHolder* threadHolder) {
    auto* kotlinThreadData = kotlin::mm::ThreadData::EvalKotlinThreadData(threadHolder);
    if (kotlinThreadData == nullptr) {
        // Race window: ThreadHolder is registered (GC-visible) but SetThreadHolder()
        // has not yet linked the ThreadData. Safe to skip — no Kotlin roots exist yet.
        // TODO(Issue #56): Eliminate race by splitting CreateAndRegister API,
        // then replace this guard with RuntimeAssert.
        return;
    }
    // Publish this mutator's global and special roots for later VisitConcurrentRoots to traverse.
    kotlinThreadData->Publish();

    kotlin::StackMapHelper stackMapHelper(*kotlinThreadData);

    auto gcPhase = reinterpret_cast<MutatorBase*>(threadHolder->GetMutator())->GetMutatorPhase();
    bool skipDerivedPointer = gcPhase != common::GCPhase::GC_PHASE_FINAL_MARK;
    if (skipDerivedPointer) {
        stackMapHelper.traverseBaseRoots(&visitor);
        return;
    }

    stackMapHelper.traverseBaseAndDerived(
            [](void* v, ObjHeader** addr) { (*reinterpret_cast<RefFieldVisitor*>(v))(reinterpret_cast<common::RefField<>&>(*addr)); },
            [](void*, uintptr_t* derivedPointerRef, ObjHeader* base, ptrdiff_t off) {
                BaseObject* object = reinterpret_cast<BaseObject*>(base);
                if (!object->IsForwarded()) {
                    return;
                }
                BaseObject* toObj = object->GetForwardingPointer();
                uintptr_t pointer = *derivedPointerRef;
                size_t objectSize = kotlin::alloc::allocatedHeapSize(reinterpret_cast<ObjHeader*>(toObj));
                bool valid = (off >= 8) && static_cast<size_t>(off) < objectSize && common::IsHeapAddress(pointer);
                if (valid) {
                    *derivedPointerRef = reinterpret_cast<uintptr_t>(toObj) + off;
                    return;
                }
                RuntimeFail("invalid derived pointer %p from %p, offset=%ld ObjectSize=%ld", (void*)pointer, base, off, objectSize);
            },
            (void*)&visitor);
}

} // namespace common
