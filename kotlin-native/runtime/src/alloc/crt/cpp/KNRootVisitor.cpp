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

#include "KNRootVisitor.hpp"
#include "RootSet.hpp"

namespace common {

std::pair<size_t, size_t> KNRootsVisitor::StackRange(kotlin::mm::ThreadData& thread) {
    // TODO: Remove useless mm::ThreadRootSet abstraction.
    FrameOverlay* currentFrame = thread.shadowStack().getCurrentFrame();
    uintptr_t minFrame = UINTPTR_MAX;
    uintptr_t maxFrame = 0;
    if (currentFrame == nullptr) {
        return std::make_pair(maxFrame, maxFrame);
    }
    while (currentFrame != nullptr) {
        if ((uintptr_t)currentFrame < minFrame) {
            minFrame = (uintptr_t)currentFrame;
        }
        if ((uintptr_t)currentFrame > maxFrame) {
            maxFrame = ((uintptr_t)currentFrame) + currentFrame->count * sizeof(ObjHeader*);
        }
        currentFrame = currentFrame->previous;
    }
    minFrame = minFrame / common::COMMON_PAGE_SIZE * common::COMMON_PAGE_SIZE;
    maxFrame = common::AlignUp<uintptr_t>(maxFrame, common::COMMON_PAGE_SIZE);
    return std::make_pair(minFrame, maxFrame);
}

void KNRootsVisitor::CollectRootSetAndFixDerivedPtr(const common::RefFieldVisitor& visitorFunc) {
    ASSERT(common::Heap::GetHeap().GetGCPhase() == common::GCPhase::GC_PHASE_FINAL_MARK);
    ForwardedRootMap preForwardRootMap;
    RecordingObjectVisitor recordingObjectVisitor{&preForwardRootMap, visitorFunc};
    FixDerivedPtrVisitor fixDerivedPtrVisitor{&preForwardRootMap};

    // copy and collect roots, record base ptr
    TraverseAllRoots(recordingObjectVisitor);

    // traverse stack roots and fix derived ptrs
    for (auto& thread : kotlin::mm::GlobalData::Instance().threadRegistry().LockForIter()) {
        thread.Publish();
        TraverseRootsOnThreadStack(fixDerivedPtrVisitor, thread);
    }
}
} // namespace common
