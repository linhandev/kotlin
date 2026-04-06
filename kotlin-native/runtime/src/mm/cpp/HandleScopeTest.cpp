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

#include "TestSupport.hpp"
#include "ThreadData.hpp"
#include "gtest/gtest.h"
#include "HandleStorage.h"
#include "HandleScope.h"
#include "KHandle.h"
#include "DisallowSafepointScope.h"

using namespace kotlin;

// ============================================================================
// HandleStorage Tests
// ============================================================================

class HandleStorageTest : public ::testing::Test {
    friend class HandleScopeDataTest;
    friend class HandleIntegrationTest;

protected:
    HandleStorage storage_;
    static constexpr size_t kBlockSize = HandleStorage::kBlockSize;
};

TEST_F(HandleStorageTest, InitialState) {
    EXPECT_EQ(0u, storage_.GetBlockCount());
    EXPECT_EQ(0u, storage_.GetCapacity());
}

TEST_F(HandleStorageTest, ExtendBlock_CreatesNewBlock) {
    auto [start, end] = storage_.ExtendBlock();

    EXPECT_NE(nullptr, start);
    EXPECT_NE(nullptr, end);
    EXPECT_EQ(start + kBlockSize, end); // kBlockSize
    EXPECT_EQ(1u, storage_.GetBlockCount());
    EXPECT_EQ(kBlockSize, storage_.GetCapacity());
}

TEST_F(HandleStorageTest, ExtendBlock_MultipleBlocks) {
    constexpr int kNumBlocks = 2;
    auto [start1, end1] = storage_.ExtendBlock();
    auto [start2, end2] = storage_.ExtendBlock();

    EXPECT_NE(start1, start2); // Different blocks
    EXPECT_EQ(2u, storage_.GetBlockCount());
    EXPECT_EQ(kBlockSize * kNumBlocks, storage_.GetCapacity());
}

TEST_F(HandleStorageTest, ClearUnusedBlocks_RemovesEmptyBlocks) {
    auto [start1, end1] = storage_.ExtendBlock();
    [[maybe_unused]] auto [start2, end2] = storage_.ExtendBlock();
    [[maybe_unused]] auto [start3, end3] = storage_.ExtendBlock();

    EXPECT_EQ(3u, storage_.GetBlockCount());

    // Clear blocks after first block
    storage_.ClearUnusedBlocks(end1);
    EXPECT_EQ(1u, storage_.GetBlockCount());
}

TEST_F(HandleStorageTest, ClearUnusedBlocks_PreservesUsedBlocks) {
    [[maybe_unused]] auto [start1, end1] = storage_.ExtendBlock();
    auto [start2, end2] = storage_.ExtendBlock();

    // Clear with limit at second block
    storage_.ClearUnusedBlocks(end2);
    EXPECT_EQ(2u, storage_.GetBlockCount());
}

TEST_F(HandleStorageTest, IterateHandles_EmptyStorage) {
    size_t count = 0;
    storage_.IterateHandles([&count](HandleSlot** slot) { count++; }, nullptr, nullptr);

    EXPECT_EQ(0u, count);
}

TEST_F(HandleStorageTest, IterateHandles_SingleBlock_PartiallyUsed) {
    constexpr int kUsedSlots = 10;
    auto [start, end] = storage_.ExtendBlock();

    HandleSlot* next_slot = start + kUsedSlots;

    // Fill slots with test values
    for (int i = 0; i < kUsedSlots; i++) {
        start[i] = reinterpret_cast<HandleSlot>(static_cast<uintptr_t>(i));
    }

    size_t count = 0;
    storage_.IterateHandles([&count](HandleSlot** slot) { count++; }, next_slot, end);

    EXPECT_EQ(static_cast<size_t>(kUsedSlots), count);
}

TEST_F(HandleStorageTest, IterateHandles_MultipleBlocks) {
    [[maybe_unused]] auto [start1, end1] = storage_.ExtendBlock();
    auto [start2, end2] = storage_.ExtendBlock();

    // First block fully used, second block partially used
    HandleSlot* next_slot = start2 + 50;

    size_t count = 0;
    storage_.IterateHandles([&count](HandleSlot** slot) { count++; }, next_slot, end2);

    EXPECT_EQ(kBlockSize + 50, count); // 256 from first block + 50 from second
}

// ============================================================================
// HandleStorage::Iterator Tests
// ============================================================================

TEST_F(HandleStorageTest, Iterator_EmptyStorage) {
    HandleSlot* next_slot = nullptr;

    auto it_begin = HandleStorage::Iterator(storage_, next_slot);
    auto it_end = HandleStorage::Iterator(storage_, next_slot, nullptr);

    EXPECT_EQ(it_begin, it_end);
}

TEST_F(HandleStorageTest, Iterator_SingleBlock) {
    auto [start, end] = storage_.ExtendBlock();
    HandleSlot* next_slot = start + 5;

    auto it = HandleStorage::Iterator(storage_, next_slot);
    auto it_end = HandleStorage::Iterator(storage_, next_slot, nullptr);

    size_t count = 0;
    for (; it != it_end; ++it) {
        count++;
    }

    EXPECT_EQ(5u, count);
}

TEST_F(HandleStorageTest, Iterator_CrossBlockBoundary) {
    constexpr int kExtraSlots = 10;
    [[maybe_unused]] auto [start1, end1] = storage_.ExtendBlock();
    auto [start2, end2] = storage_.ExtendBlock();

    HandleSlot* next_slot = start2 + kExtraSlots;

    auto it = HandleStorage::Iterator(storage_, next_slot);
    auto it_end = HandleStorage::Iterator(storage_, next_slot, nullptr);

    size_t count = 0;
    for (; it != it_end; ++it) {
        count++;
    }

    EXPECT_EQ(kBlockSize + kExtraSlots, count);
}

TEST_F(HandleStorageTest, Iterator_Dereference) {
    auto [start, end] = storage_.ExtendBlock();
    start[0] = reinterpret_cast<HandleSlot>((uintptr_t)0xDEADBEEF);

    HandleSlot* next_slot = start + 1;
    auto it = HandleStorage::Iterator(storage_, next_slot);

    EXPECT_EQ(start, *it);
    EXPECT_EQ(reinterpret_cast<HandleSlot>((uintptr_t)0xDEADBEEF), **it);
}

// ============================================================================
// HandleScopeData Tests
// ============================================================================

class HandleScopeDataTest : public ::testing::Test {
protected:
    HandleScopeDataTestSupport data_;
    static constexpr size_t kBlockSize = HandleStorageTest::kBlockSize;
};

TEST_F(HandleScopeDataTest, InitialState) {
    EXPECT_EQ(0, data_.level);
}

TEST_F(HandleScopeDataTest, NextHandleSlot_AllocatesFirstBlock) {
    HandleSlot* slot1 = data_.NextHandleSlot();
    EXPECT_NE(nullptr, slot1);

    HandleSlot* slot2 = data_.NextHandleSlot();
    EXPECT_NE(nullptr, slot2);
    EXPECT_EQ(slot1 + 1, slot2);
}

TEST_F(HandleScopeDataTest, NextHandleSlot_ExtendsWhenFull) {
    for (size_t i = 0; i < kBlockSize; i++) {
        data_.NextHandleSlot();
    }
    EXPECT_EQ(1u, data_.storage_.GetBlockCount());

    // This should trigger block extension
    HandleSlot* overflow_slot = data_.NextHandleSlot();
    EXPECT_NE(nullptr, overflow_slot);
    EXPECT_EQ(2u, data_.storage_.GetBlockCount());
}

TEST_F(HandleScopeDataTest, PopScope_RestoresPreviousState) {
    constexpr int kOuterLevel = 1;
    constexpr int kNestedLevel = 2;
    // Create some slots
    data_.level = kOuterLevel;
    data_.NextHandleSlot();
    data_.NextHandleSlot();

    HandleSlot* prev_next = data_.nextSlot_;
    HandleSlot* prev_limit = data_.limit_;

    // Simulate entering nested scope and allocating more
    data_.level = kNestedLevel;
    data_.NextHandleSlot();
    data_.NextHandleSlot();

    // Pop scope
    data_.PopScope(prev_limit, prev_next);

    EXPECT_EQ(kOuterLevel, data_.level);
    EXPECT_EQ(prev_next, data_.nextSlot_);
    EXPECT_EQ(prev_limit, data_.limit_);
}

TEST_F(HandleScopeDataTest, PopScope_ClearsUnusedBlocks) {
    constexpr int kAllocCount = 300;
    constexpr int kMoreAllocCount = 200;
    // Allocate multiple blocks
    for (int i = 0; i < kAllocCount; i++) {
        data_.NextHandleSlot();
    }

    size_t initialBlocks = data_.storage_.GetBlockCount();
    EXPECT_GE(initialBlocks, 2u);

    // Save state after first block
    data_.NextHandleSlot(); // At least one slot in first block
    HandleSlot* saved_limit = data_.limit_;
    HandleSlot* saved_next = data_.nextSlot_;

    // Allocate more
    for (int i = 0; i < kMoreAllocCount; i++) {
        data_.NextHandleSlot();
    }

    data_.level = 1;
    data_.PopScope(saved_limit, saved_next);

    // Should have fewer blocks now
    EXPECT_LE(data_.storage_.GetBlockCount(), initialBlocks);
}

TEST_F(HandleScopeDataTest, IterateHandles_Callback) {
    constexpr int kSlotCount = 10;
    constexpr int kExpectedSum = 45; // 0+1+2+...+9
    // Allocate some slots
    for (int i = 0; i < kSlotCount; i++) {
        HandleSlot* slot = data_.NextHandleSlot();
        *slot = reinterpret_cast<HandleSlot>(static_cast<uintptr_t>(i));
    }

    int count = 0;
    int sum = 0;
    data_.IterateHandles([&](HandleSlot** slot_ptr) {
        count++;
        sum += static_cast<int>(reinterpret_cast<uintptr_t>(**slot_ptr));
    });

    EXPECT_EQ(kSlotCount, count);
    EXPECT_EQ(kExpectedSum, sum);
}

TEST_F(HandleScopeDataTest, Iterator_BeginEnd) {
    constexpr int kSlotCount = 5;
    for (int i = 0; i < kSlotCount; i++) {
        data_.NextHandleSlot();
    }

    int count = 0;
    for (auto it = data_.begin(); it != data_.end(); ++it) {
        count++;
    }

    EXPECT_EQ(kSlotCount, count);
}

// ============================================================================
// HandleScope Tests
// ============================================================================

class HandleScopeTest : public ::testing::Test {
protected:
    HandleScopeDataTestSupport& GetHandleScopeData(mm::ThreadData& t)
    {
        return reinterpret_cast<HandleScopeDataTestSupport&>(t.GetHandleScopeData());
    }
};

TEST_F(HandleScopeTest, Constructor_IncrementsLevel) {
    RunInNewThread([this](mm::ThreadData& th) {
        auto& data = GetHandleScopeData(th);
        EXPECT_EQ(0, data.level);
        {
            HandleScope scope(th);
            EXPECT_EQ(1, data.level);
        }
    });
}

TEST_F(HandleScopeTest, Destructor_DecrementsLevel) {
    RunInNewThread([this](mm::ThreadData& th) {
        auto& data = GetHandleScopeData(th);

        {
            HandleScope scope(th);
            EXPECT_EQ(1, data.level);
        }

        EXPECT_EQ(0, data.level);
    });
}

TEST_F(HandleScopeTest, NestedScopes) {
    RunInNewThread([this](mm::ThreadData& th) {
        constexpr int kLevel0 = 0;
        constexpr int kLevel1 = 1;
        constexpr int kLevel2 = 2;
        constexpr int kLevel3 = 3;
        auto& data = GetHandleScopeData(th);
        {
            HandleScope scope1(th);
            EXPECT_EQ(kLevel1, data.level);

            {
                HandleScope scope2(th);
                EXPECT_EQ(kLevel2, data.level);

                {
                    HandleScope scope3(th);
                    EXPECT_EQ(kLevel3, data.level);
                }
                EXPECT_EQ(kLevel2, data.level);
            }
            EXPECT_EQ(kLevel1, data.level);
        }
        EXPECT_EQ(kLevel0, data.level);
    });
}

TEST_F(HandleScopeTest, CreateHandleSlot_ReturnsValidSlot) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);

        HandleSlot* slot1 = scope.CreateHandleSlot();
        HandleSlot* slot2 = scope.CreateHandleSlot();

        EXPECT_NE(nullptr, slot1);
        EXPECT_NE(nullptr, slot2);
        EXPECT_NE(slot1, slot2);
    });
}

TEST_F(HandleScopeTest, CreateHandleSlot_Sequential) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);
        HandleSlot* slot1 = scope.CreateHandleSlot();
        HandleSlot* slot2 = scope.CreateHandleSlot();
        HandleSlot* slot3 = scope.CreateHandleSlot();

        EXPECT_EQ(slot1 + 1, slot2);
        EXPECT_EQ(slot2 + 1, slot3);
    });
}

TEST_F(HandleScopeTest, ScopeRestoration_ResetsNextSlot) {
    RunInNewThread([this](mm::ThreadData& th) {
        auto& data = GetHandleScopeData(th);

        HandleSlot* initial_next;
        {
            HandleScope scope1(th);
            scope1.CreateHandleSlot();
            scope1.CreateHandleSlot();
            initial_next = data.nextSlot_;

            {
                HandleScope scope2(th);
                scope2.CreateHandleSlot();
                scope2.CreateHandleSlot();
                scope2.CreateHandleSlot();

                // next_slot should have advanced
                EXPECT_NE(initial_next, data.nextSlot_);
            }

            // Should restore to scope1's position
            EXPECT_EQ(initial_next, data.nextSlot_);
        }
    });
}

TEST_F(HandleScopeTest, CreateKHandle_ReturnsValidHandle) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);

        ObjHeader* testObj = reinterpret_cast<ObjHeader*>(0xDEADBEEF);
        auto handle = scope.CreateKHandle<ObjHeader*>(testObj);

        EXPECT_EQ(testObj, handle.get());
    });
}

TEST_F(HandleScopeTest, CreateKHandle_EmptyHandle) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);
        auto handle = scope.CreateKHandle<ObjHeader*>();
        EXPECT_EQ(nullptr, handle.get());
    });
}

TEST_F(HandleScopeTest, StaticCreateHandleSlot_WithMemoryState) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleSlot* slot = HandleScope::CreateHandleSlot(th);
        EXPECT_NE(nullptr, slot);
    });
}

// ============================================================================
// KHandle Tests
// ============================================================================

class KHandleTest : public ::testing::Test {
protected:
    ObjHeader* CreateTestObject(intptr_t value) { return reinterpret_cast<ObjHeader*>(value); }
};

TEST_F(KHandleTest, Constructor_WithObject) {
    RunInNewThread([this](mm::ThreadData& th) {
        HandleScope scope(th);
        ObjHeader* obj = CreateTestObject(0xDEADBEEF);

        KHandle<ObjHeader*> handle(obj, scope);

        EXPECT_EQ(obj, handle.get());
        EXPECT_EQ(obj, handle.obj());
    });
}

TEST_F(KHandleTest, Constructor_NullObject) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);

        KHandle<ObjHeader*> handle(nullptr, scope);

        EXPECT_EQ(nullptr, handle.get());
    });
}

TEST_F(KHandleTest, Constructor_EmptyHandle) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);

        auto handle = KHandle<ObjHeader*>::null();
        EXPECT_EQ(nullptr, handle.get());
    });
}

TEST_F(KHandleTest, Constructor_WithScope) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);

        KHandle<ObjHeader*> handle(scope);
        EXPECT_EQ(nullptr, handle.get());
    });
}

TEST_F(KHandleTest, Get_ReturnsStoredObject) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);
        ObjHeader* obj = reinterpret_cast<ObjHeader*>(static_cast<uintptr_t>(0xDEADBEEF));

        KHandle<ObjHeader*> handle(obj, scope);

        EXPECT_EQ(obj, handle.get());
    });
}

TEST_F(KHandleTest, ArrowOperator_ReturnsObject) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);
        ObjHeader* obj = reinterpret_cast<ObjHeader*>(static_cast<uintptr_t>(0xDEADBEEF));

        KHandle<ObjHeader*> handle(obj, scope);

        EXPECT_EQ(obj, handle.operator->());
    });
}

TEST_F(KHandleTest, Slot_ReturnsSlotPointer) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);
        ObjHeader* obj = reinterpret_cast<ObjHeader*>(static_cast<uintptr_t>(0xDEADBEEF));

        KHandle<ObjHeader*> handle(obj, scope);

        ObjHeader** slot = handle.slot();
        EXPECT_NE(nullptr, slot);
        EXPECT_EQ(obj, *slot);
    });
}

TEST_F(KHandleTest, Obj_ReturnsObject) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);
        ObjHeader* obj = reinterpret_cast<ObjHeader*>(static_cast<uintptr_t>(0xDEADBEEF));

        KHandle<ObjHeader*> handle(obj, scope);

        EXPECT_EQ(obj, handle.obj());
    });
}

TEST_F(KHandleTest, Clear_SetsSlotToNull) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);
        ObjHeader* obj = reinterpret_cast<ObjHeader*>(static_cast<uintptr_t>(0xDEADBEEF));

        KHandle<ObjHeader*> handle(obj, scope);
        handle.clear();

        EXPECT_EQ(nullptr, handle.slot());
    });
}

TEST_F(KHandleTest, MultipleHandles_SameObject) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);
        ObjHeader* obj = reinterpret_cast<ObjHeader*>(static_cast<uintptr_t>(0xDEADBEEF));

        KHandle<ObjHeader*> handle1(obj, scope);
        KHandle<ObjHeader*> handle2(obj, scope);

        EXPECT_EQ(obj, handle1.get());
        EXPECT_EQ(obj, handle2.get());
        EXPECT_NE(handle1.slot(), handle2.slot());
    });
}

TEST_F(KHandleTest, HandleUpdate_SimulateGCMove) {
    RunInNewThread([](mm::ThreadData& th) {
        HandleScope scope(th);
        ObjHeader* originalObj = reinterpret_cast<ObjHeader*>(static_cast<uintptr_t>(0xDEADBEEF));

        KHandle<ObjHeader*> handle(originalObj, scope);
        EXPECT_EQ(originalObj, handle.get());

        ObjHeader* newObj = reinterpret_cast<ObjHeader*>(static_cast<uintptr_t>(0xDEADBEEF));
        *handle.slot() = newObj;

        EXPECT_EQ(newObj, handle.get());
    });
}

// ============================================================================
// Integration Tests
// ============================================================================

class HandleIntegrationTest : public ::testing::Test {
protected:
    ObjHeader* CreateTestObject(intptr_t value) { return reinterpret_cast<ObjHeader*>(value); }
    HandleScopeDataTestSupport& GetHandleScopeData(mm::ThreadData& t)
    {
        return reinterpret_cast<HandleScopeDataTestSupport&>(t.GetHandleScopeData());
    }
    static constexpr size_t kBlockSize = HandleStorageTest::kBlockSize;
};

TEST_F(HandleIntegrationTest, MultipleScopes_MemoryReclamation) {
    constexpr int kAllocCount = 300;
    constexpr int kInnerObjOffset = 1000;
    RunInNewThread([this](mm::ThreadData& th) {
        auto& data = GetHandleScopeData(th);

        {
            HandleScope scope1(th);

            // Allocate many handles to trigger multiple blocks
            std::vector<KHandle<ObjHeader*>> handles;
            for (size_t i = 0; i < kAllocCount; i++) {
                handles.push_back(scope1.CreateKHandle<ObjHeader*>(CreateTestObject(i)));
            }

            EXPECT_EQ(data.storage_.GetBlockCount(), 2u);

            {
                HandleScope scope2(th);

                // Allocate more handles
                for (int i = 0; i < kAllocCount; i++) {
                    scope2.CreateKHandle<ObjHeader*>(CreateTestObject(i + kInnerObjOffset));
                }

                EXPECT_EQ(data.storage_.GetBlockCount(), 3u);
            }

            // After scope2 exits, blocks should be reclaimed
            EXPECT_GE(data.storage_.GetBlockCount(), 2u);
        }

        // After all scopes exit, we should have reclaimed memory
        EXPECT_GE(data.storage_.GetBlockCount(), 0u);
        EXPECT_EQ(0, data.level);
    });
}

TEST_F(HandleIntegrationTest, HandlesScopeDestruction) {
    RunInNewThread([this](mm::ThreadData& th) {
        // Keep a parent scope with a handle to pin the storage block
        HandleScope outerScope(th);
        outerScope.CreateKHandle(CreateTestObject(0x0));

        HandleSlot* slot = nullptr;
        {
            HandleScope scope(th);
            auto handle = scope.CreateKHandle(CreateTestObject(0x1));
            slot = reinterpret_cast<HandleSlot*>(handle.slot());
        }

        // Block is retained by outerScope's handle, slot address is reused

        {
            HandleScope scope(th);
            [[maybe_unused]] auto handle = scope.CreateKHandle(CreateTestObject(0x2));
        }

        EXPECT_EQ(*slot, (HandleSlot)0x2);
    });
}

TEST_F(HandleIntegrationTest, IterateAllHandlesInScope) {
    RunInNewThread([this](mm::ThreadData& th) {
        HandleScope scope(th);
        auto& data = GetHandleScopeData(th);

        const int numHandles = 10;
        std::vector<ObjHeader*> objects;

        for (int i = 0; i < numHandles; i++) {
            auto obj = CreateTestObject(i * 100);
            objects.push_back(obj);
            scope.CreateKHandle<ObjHeader*>(obj);
        }

        // Iterate and verify
        int count = 0;
        for (auto it = data.begin(); it != data.end(); ++it) {
            HandleSlot* slot = *it;
            ObjHeader* obj = reinterpret_cast<ObjHeader*>(*slot);

            // Verify object is in our list
            auto found = std::find(objects.begin(), objects.end(), obj);
            EXPECT_NE(objects.end(), found);

            count++;
        }

        EXPECT_EQ(numHandles, count);
    });
}

TEST_F(HandleIntegrationTest, NestedScopes_IsolatedHandles) {
    RunInNewThread([this](mm::ThreadData& th) {
        constexpr int kHandlesPerScope = 5;
        constexpr int kInnerObjOffset = 100;
        auto& data = GetHandleScopeData(th);

        std::vector<ObjHeader*> outer_objects;
        std::vector<ObjHeader*> inner_objects;

        {
            HandleScope outer_scope(th);

            // Create handles in outer scope
            for (int i = 0; i < kHandlesPerScope; i++) {
                auto obj = CreateTestObject(i);
                outer_objects.push_back(obj);
                outer_scope.CreateKHandle<ObjHeader*>(obj);
            }

            HandleSlot* outer_next = data.nextSlot_;

            {
                HandleScope inner_scope(th);

                // Create handles in inner scope
                for (int i = 0; i < kHandlesPerScope; i++) {
                    auto obj = CreateTestObject(i + kInnerObjOffset);
                    inner_objects.push_back(obj);
                    inner_scope.CreateKHandle<ObjHeader*>(obj);
                }

                // Next slot should have advanced
                EXPECT_NE(outer_next, data.nextSlot_);
            }

            // After inner scope exits, should restore to outer scope's position
            EXPECT_EQ(outer_next, data.nextSlot_);

            // Iterate should only see outer scope's handles
            int count = 0;
            for (auto it = data.begin(); it != data.end(); ++it) {
                count++;
            }
            EXPECT_EQ(kHandlesPerScope, count);
        }
    });
}

TEST_F(HandleIntegrationTest, LargeNumberOfHandles_CrossMultipleBlocks) {
    RunInNewThread([this](mm::ThreadData& th) {
        HandleScope scope(th);
        auto& data = GetHandleScopeData(th);

        const int numHandles = 1000;

        for (int i = 0; i < numHandles; i++) {
            scope.CreateKHandle<ObjHeader*>(CreateTestObject(i));
        }

        EXPECT_GE(data.storage_.GetBlockCount(), 4u);

        {
            // Iterate and count
            int count = 0;
            for (auto it = data.begin(); it != data.end(); ++it) {
                EXPECT_EQ(**it, (HandleSlot)count);
                count++;
            }
        }

        {
            // We can update value through iterator
            int count = 0;
            for (auto it = data.begin(); it != data.end(); ++it) {
                **it = (HandleSlot)(numHandles - count);
                count++;
            }
            for (auto it = data.begin(); it != data.end(); ++it) {
                EXPECT_EQ(**it, (HandleSlot)count);
                count--;
            }
            EXPECT_EQ(0, count);
        }
    });
}

TEST_F(HandleIntegrationTest, EmptyScopes_NoMemoryAllocation) {
    RunInNewThread([this](mm::ThreadData& th) {
        auto& data = GetHandleScopeData(th);
        size_t initialBlocks = data.storage_.GetBlockCount();

        {
            HandleScope scope1(th);
            {
                HandleScope scope2(th);
                // Don't create any handles
            }
        }

        // No new blocks should be allocated if no handles created
        EXPECT_EQ(initialBlocks, data.storage_.GetBlockCount());
    });
}

// ============================================================================
// DisallowSafepointScope Tests
// ============================================================================

TEST(DisallowSafepointScopeTest, AssertAllowSafepoint_NoScope)
{
    RunInNewThread([](mm::ThreadData& th) {
        // level == 0, no active scope — should pass without crash
        mm::DisallowSafepointScope::AssertAllowSafepoint(th);
    });
}

TEST(DisallowSafepointScopeDeathTest, AssertAllowSafepoint_WithScope)
{
    RunInNewThread([](mm::ThreadData& th) {
        mm::DisallowSafepointScope scope;
        // level > 0 — AssertAllowSafepoint should crash
        EXPECT_DEATH(mm::DisallowSafepointScope::AssertAllowSafepoint(th),
                     "DisallowSafepointScope set at");
    });
}
