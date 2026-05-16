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
#ifndef RUNTIME_HANDLE_STORAGE_H
#define RUNTIME_HANDLE_STORAGE_H

#include <vector>
#include <array>
#include "Types.h"

struct MemoryState;

// A HandleStorage is a per-HandleScopeData unit, which manages the memory for handle slots.
class HandleStorage {
    friend class HandleStorageTest;
    // Number of slots per block
    static constexpr size_t kBlockSize = 256;
    // Each block is a continuous memory
    using Block = std::array<HandleSlot, kBlockSize>;

public:
    HandleStorage() {};

    ~HandleStorage()
    {
        for (auto block : blocks_) {
            delete block;
        }
    }

    // Extend one block, returns the start and end
    std::pair<HandleSlot*, HandleSlot*> ExtendBlock()
    {
        auto block = new Block;
        blocks_.push_back(block);
        return std::make_pair(block->data(), block->data() + block->size());
    }

    // Clear blocks that are completely unused to reclaim memory,
    void ClearUnusedBlocks(HandleSlot* limit)
    {
        while (!blocks_.empty()) {
            auto block = blocks_.back();
            auto blockEnd = block->data() + block->size();
            if (limit == blockEnd) {
                break;
            }
            delete block;
            blocks_.pop_back();
        }
    }

    // Provide a map function over all active slots.
    template <typename Callback>
    void IterateHandles(Callback callback, HandleSlot* nextSlot, HandleSlot* limit)
    {
        for (auto* block : blocks_) {
            HandleSlot* blockStart = block->data();
            HandleSlot* blockEnd = blockStart + block->size();

            if (blockEnd == limit) {
                // This is the partially used block.
                for (HandleSlot* slot = blockStart; slot < nextSlot; ++slot) {
                    callback(&slot);
                }
                return;
            } else {
                // Fully used block, traverse all slots in this block.
                for (HandleSlot* slot = blockStart; slot < blockEnd; ++slot) {
                    callback(&slot);
                }
            }
        }
    }

    class Iterator {
    public:
        using iterator_category = std::forward_iterator_tag;
        using value_type = HandleSlot*;
        using difference_type = std::ptrdiff_t;
        using pointer = value_type*;
        using reference = value_type&;

        explicit Iterator(const HandleStorage& storage, HandleSlot* nextSlot)
            : blocks_(&storage.blocks_), blockIdx_(0), current_(nullptr), end_(nextSlot)
        {
            if (blockIdx_ < blocks_->size()) {
                current_ = (*blocks_)[blockIdx_]->data();
            }
        }

        // Used for creating an iterator with a specific current value
        explicit Iterator(
                const HandleStorage& storage,
                HandleSlot* nextSlot,
                HandleSlot* current)
            : blocks_(&storage.blocks_), blockIdx_(0), current_(current), end_(nextSlot) {}

        HandleSlot* operator*() const { return current_; }

        Iterator& operator++()
        {
            current_++;

            // Check if we hit end
            if (current_ == end_) {
                SetEnd();
                return *this;
            }

            // check if we hit end of a used block, if so, to next block
            auto block = (*blocks_)[blockIdx_];
            HandleSlot* blockEnd = block->data() + block->size();
            if (current_ == blockEnd) {
                blockIdx_++;
                if (blockIdx_ < blocks_->size()) {
                    current_ = (*blocks_)[blockIdx_]->data();
                } else {
                    SetEnd();
                }
            }
            return *this;
        }

        bool operator!=(const Iterator& other) const { return current_ != other.current_; }
        bool operator==(const Iterator& other) const { return current_ == other.current_; }

    private:
        void SetEnd() { current_ = nullptr; }

        const std::vector<HandleStorage::Block*>* blocks_;
        size_t blockIdx_;
        HandleSlot* current_;
        HandleSlot* end_;
    };

    // Statistics
    size_t GetBlockCount() const { return blocks_.size(); }
    size_t GetCapacity() const { return blocks_.size() * kBlockSize; }

private:
    std::vector<Block*> blocks_;
};

#endif // RUNTIME_HANDLE_STORAGE_H
