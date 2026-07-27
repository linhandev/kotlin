/*
 * Copyright 2024 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "MemoryDump.hpp"

#include <csignal>
#include <unistd.h>
#include <algorithm>
#include <atomic>
#include <cerrno>
#include <cstdio>
#include <cstring>
#include <limits>
#include <mutex>
#include <system_error>
#include <thread>
#include <vector>
#include <queue>
#include <cinttypes>
#include <zlib.h>
#include <chrono>
#include "Logging.hpp"
#include "Porting.h"
#include "TypeInfo.h"
#include "Types.h"
#include "KString.h"
#include "ObjectTraversal.hpp"
#include "GlobalData.hpp"
#include "RootSet.hpp"
#include "ThreadData.hpp"
#include "std_support/Span.hpp"

#ifdef KONAN_OHOS
#include "hilog/log.h"
#endif

#ifdef ENABLE_CRT
#include "crt/cpp/HeapInterface.hpp"
#include "crt/cpp/KNBaseObject.hpp"
#include "common_components/heap/allocator/region_desc.h"
#include "common_components/heap/allocator/region_manager.h"
#include "common_components/heap/heap.h"
#endif

constexpr auto kTagMemDump = kotlin::logging::Tag::kMemoryDump;

// Bytes-to-MB conversion constant for log messages.
constexpr size_t kBytesPerMB = 1024 * 1024;
// Bytes-to-MB conversion constant (double), for floating-point contexts.
constexpr double kBytesPerMBDouble = 1024.0 * 1024.0;
// Bytes-to-KB conversion constant (double).
constexpr double kBytesPerKBDouble = 1024.0;
// Maximum number of parallel compression/dump threads.
constexpr size_t kMaxConcurrency = 16;
// Minimum number of parallel compression/dump threads.
constexpr size_t kMinThreads = 2;
// Percentage multiplier for compression ratio calculation.
constexpr double kPercentMultiplier = 100.0;

namespace kotlin::mm {

namespace {
// Global flag ensuring only one dump runs at a time.
std::atomic<bool> gDumpInProgress{false};
} // namespace

DumpGuard::DumpGuard() noexcept : acquired_(false) {
    bool expected = false;
    acquired_ = gDumpInProgress.compare_exchange_strong(
        expected, true, std::memory_order_acq_rel);
    if (!acquired_) {
        RuntimeLogInfo({kTagMemDump}, "Another memory dump is in progress, skipping.");
    }
}

DumpGuard::~DumpGuard() {
    if (acquired_) {
        gDumpInProgress.store(false, std::memory_order_release);
    }
}

// using PointerSet replace std::unordered_set,
// speed up insert and lookup operations,
//  and reduce memory overhead by avoiding node allocations;
class PointerSet {
public:
    static const size_t kExpansionFactor = 2;
    explicit PointerSet(size_t capacity = 1024) : size_(0) {
        size_t tableSize = 1;
        while (tableSize < capacity * kExpansionFactor) {
            tableSize <<= 1;
        }
        table_.resize(tableSize, nullptr);
        mask_ = tableSize - 1;
    }

    bool Insert(const void* ptr) {
        if (NeedsResize()) {
            Resize();
        }
        size_t idx = Hash(ptr);
        size_t probe = 0;
        while (true) {
            const void*& slot = table_[idx];
            if (slot == nullptr) {
                slot = ptr;
                ++size_;
                return true;
            }
            if (slot == ptr) {
                return false;
            }
            ++probe;
            if (probe > mask_) {
                return false;
            }
            idx = (idx + probe) & mask_;
        }
        return false;
    }

    bool Contains(const void* ptr) const {
        size_t idx = Hash(ptr);
        size_t probe = 0;
        while (true) {
            const void* slot = table_[idx];
            if (slot == nullptr) {
                return false;
            }
            if (slot == ptr) {
                return true;
            }
            ++probe;
            if (probe > mask_) {
                return false;
            }
            idx = (idx + probe) & mask_;
        }
        return false;
    }

    size_t Size() const { return size_; }

    size_t Capacity() const { return table_.size(); }

    void Reserve(size_t capacity) {
        size_t tableSize = 1;
        while (tableSize < capacity * kExpansionFactor) {
            tableSize <<= 1;
        }
        if (tableSize > table_.size()) {
            table_.clear();
            table_.resize(tableSize, nullptr);
            mask_ = tableSize - 1;
            size_ = 0;
        }
    }

    // Iterates all non-null entries. Thread-safe for concurrent reads
    // (no concurrent writes allowed).
    template <typename F>
    void ForEach(F&& fn) const {
        for (const void* ptr : table_) {
            if (ptr != nullptr) {
                fn(ptr);
            }
        }
    }

    // Bulk-insert all entries from |other|. Caller must ensure
    // |this| has enough capacity to avoid mid-merge resizes.
    void MergeFrom(const PointerSet& other) {
        other.ForEach([this](const void* ptr) { Insert(ptr); });
    }

private:
    static constexpr double kMaxLoadFactor = 0.7;
    static constexpr int kShiftBits = 3; // Assuming 8-byte alignment, so lower 3 bits are zero.

    size_t Hash(const void* ptr) const { return (reinterpret_cast<size_t>(ptr) >> kShiftBits) & mask_; }

    bool NeedsResize() const { return static_cast<double>(size_) / table_.size() >= kMaxLoadFactor; }

    void Resize() {
        size_t newTableSize = table_.size() << 1;
        std::vector<const void*> oldTable = std::move(table_);
        table_.clear();
        table_.resize(newTableSize, nullptr);
        mask_ = newTableSize - 1;
        size_ = 0;

        for (const void* ptr : oldTable) {
            if (ptr != nullptr) {
                InsertNoResize(ptr);
            }
        }
    }

    void InsertNoResize(const void* ptr) {
        size_t idx = Hash(ptr);
        size_t probe = 0;
        while (true) {
            const void*& slot = table_[idx];
            if (slot == nullptr) {
                slot = ptr;
                ++size_;
                return;
            }
            ++probe;
            if (probe > mask_) {
                return;
            }
            idx = (idx + probe) & mask_;
        }
        return;
    }

    std::vector<const void*> table_;
    size_t mask_;
    size_t size_;
};

// MemoryBuffer collects all dump data in memory before compression.
// This separates the dump phase from the compression phase,
// allowing precise timing of each step.
class MemoryBuffer {
public:
    void Write(const void* data, size_t size) {
        const char* ptr = static_cast<const char*>(data);
        data_.insert(data_.end(), ptr, ptr + size);
    }

    // Pre-allocate internal buffer to avoid repeated reallocations.
    void Reserve(size_t capacity) {
        data_.reserve(capacity);
    }

    // Appends all data from |other| into this buffer.
    void Append(const MemoryBuffer& other) {
        if (!other.data_.empty()) {
            data_.insert(data_.end(), other.data_.begin(), other.data_.end());
        }
    }

    // Raw data access (read-only).
    const char* Data() const { return data_.data(); }
    size_t Size() const { return data_.size(); }

    // Writes the buffer contents to fd and clears the buffer.
    // Returns the number of bytes written. Throws on write failure.
    size_t FlushToFd(int fd) {
        if (data_.empty()) { return 0; }
        size_t written = data_.size();
        const char* ptr = data_.data();
        size_t remaining = written;
        while (remaining > 0) {
            ssize_t n = write(fd, ptr, remaining);
            if (n < 0) {
                if (errno == EINTR) { continue; }
                throw std::system_error(errno, std::generic_category());
            }
            ptr += n;
            remaining -= static_cast<size_t>(n);
        }
        data_.clear();
        return written;
    }

    // Compresses collected data to fd using multiple threads.
    // The data is split into chunks; each chunk is compressed independently
    // as a gzip member, then concatenated to the output fd. Gzip decompressors
    // natively handle concatenated gzip members (equivalent to "cat *.gz").
    void CompressToFile(int fd) const {
        if (data_.empty()) { return; }

        size_t totalSize = data_.size();
        if (totalSize == 0) { return; }

        // Use single-threaded path for small dumps to avoid thread overhead.
        constexpr size_t kMinParallelSize = 2 * 1024 * 1024;  // 2 MB
        if (totalSize < kMinParallelSize) {
            CompressSingleThreaded(fd, totalSize);
            return;
        }
        CompressParallel(fd, totalSize);
    }

    // Single-threaded compression path for small dumps.
    void CompressSingleThreaded(int fd, size_t totalSize) const {
        using Clock = std::chrono::high_resolution_clock;
        auto t0 = Clock::now();
        std::vector<char> out;
        CompressChunkToGzip(data_.data(), totalSize, out);
        auto t1 = Clock::now();
        WriteAll(fd, out.data(), out.size());
        auto t2 = Clock::now();
        RuntimeLogInfo({kTagMemDump},
            "  compress/single: deflate=%.2f ms, write=%.2f ms",
            std::chrono::duration<double, std::milli>(t1 - t0).count(),
            std::chrono::duration<double, std::milli>(t2 - t1).count());
    }

    // Multi-threaded compression path: splits data into chunks, compresses
    // each chunk in a separate thread, then writes results to fd in order.
    void CompressParallel(int fd, size_t totalSize) const {
        using Clock = std::chrono::high_resolution_clock;
        if (totalSize == 0) { return; }

        // Determine thread count: clamped to [kMinThreads, kMaxConcurrency].
        size_t numChunks = std::max(kMinThreads, static_cast<size_t>(std::thread::hardware_concurrency()));
        if (numChunks > kMaxConcurrency) { numChunks = kMaxConcurrency; }

        // Ensure each chunk is at least 8 MB to amortize thread-creation cost.
        constexpr size_t kMinChunkSize = 8 * 1024 * 1024;
        size_t maxChunks = totalSize / kMinChunkSize;
        if (maxChunks < 1) { maxChunks = 1; }
        if (numChunks > maxChunks) { numChunks = maxChunks; }
        if (numChunks == 0) { return; }

        size_t chunkSize = (totalSize + numChunks - 1) / numChunks;

        // Pre-allocate output buffers and thread vector.
        std::vector<std::vector<char>> compressedChunks(numChunks);
        std::vector<std::thread> threads;
        threads.reserve(numChunks);

        // Dispatch compression threads.
        auto t0 = Clock::now();
        for (size_t i = 0; i < numChunks; ++i) {
            size_t offset = i * chunkSize;
            size_t size = std::min(chunkSize, totalSize - offset);
            if (size == 0) { continue; }
            threads.emplace_back([this, offset, size, &compressedChunks, i]() {
                CompressChunkToGzip(data_.data() + offset, size, compressedChunks[i]);
            });
        }
        auto t1 = Clock::now();

        // Wait for all threads to finish.
        for (auto& t : threads) {
            t.join();
        }
        auto t2 = Clock::now();

        // Write compressed chunks to fd in order.
        size_t totalCompressed = 0;
        for (const auto& chunk : compressedChunks) {
            if (!chunk.empty()) {
                totalCompressed += chunk.size();
                WriteAll(fd, chunk.data(), chunk.size());
            }
        }
        auto t3 = Clock::now();

        RuntimeLogInfo({kTagMemDump},
            "  compress/parallel: threads=%zu, chunk=%.1f MB, "
            "dispatch=%.2f ms, deflate=%.2f ms, write=%.2f ms, "
            "raw=%zu MB, compressed=%zu MB, ratio=%.1f%%",
            numChunks, chunkSize / kBytesPerMBDouble,
            std::chrono::duration<double, std::milli>(t1 - t0).count(),
            std::chrono::duration<double, std::milli>(t2 - t1).count(),
            std::chrono::duration<double, std::milli>(t3 - t2).count(),
            totalSize / kBytesPerMB, totalCompressed / kBytesPerMB,
            kPercentMultiplier * totalCompressed / totalSize);
    }

private:
    // Compresses [data, data+size) into |output| as a standalone gzip member.
    // Uses Z_BEST_SPEED to match the existing gzdopen("w1") behaviour.
    static void CompressChunkToGzip(const char* data, size_t size,
                                    std::vector<char>& output) {
        z_stream strm{};

        // MAX_WBITS + 16 = gzip format (header + trailer generated automatically).
        // Level 6: zlib default, good balance of speed and compression.
        // Targets 500MB raw → <100MB compressed. Level 1 was ~27% ratio,
        // level 6 provides ~25-30% better compression at ~2x slower deflate.
        int ret = deflateInit2(&strm, Z_BEST_SPEED, Z_DEFLATED,
                               MAX_WBITS + 16, MAX_MEM_LEVEL, Z_DEFAULT_STRATEGY);
        if (ret != Z_OK) {
            output.clear();
            return;
        }

        strm.next_in = const_cast<Bytef*>(reinterpret_cast<const Bytef*>(data));
        strm.avail_in = static_cast<uInt>(size);

        // Allocate worst-case output buffer.
        uLong bound = deflateBound(&strm, static_cast<uLong>(size));
        output.resize(bound);

        strm.next_out = reinterpret_cast<Bytef*>(output.data());
        strm.avail_out = static_cast<uInt>(output.size());

        ret = deflate(&strm, Z_FINISH);
        if (ret == Z_STREAM_END) {
            // Trim to actual compressed size.
            output.resize(strm.total_out);
        } else {
            output.clear();  // compression failed → empty chunk, skipped on write
        }

        deflateEnd(&strm);
    }

    // Writes all |size| bytes from |data| to |fd|, retrying on partial writes
    // and EINTR. Throws std::system_error on unrecoverable write errors.
    static void WriteAll(int fd, const void* data, size_t size) {
        const char* ptr = static_cast<const char*>(data);
        while (size > 0) {
            ssize_t written = write(fd, ptr, size);
            if (written < 0) {
                if (errno == EINTR) { continue; }
                throw std::system_error(errno, std::generic_category());
            }
            ptr += written;
            size -= static_cast<size_t>(written);
        }
    }

    std::vector<char> data_;
};

class MemoryDumper {
public:
    static constexpr size_t kInitialObjectSetCapacity = 0x1000000; // 8M objects
    static constexpr size_t kInitialTypeSetCapacity = 4096;

    explicit MemoryDumper(bool isStrip, int streamFd = -1) : isStrip_(isStrip), streamFd_(streamFd) {
        dumpedObjs_.Reserve(kInitialObjectSetCapacity);
        dumpedTypes_.Reserve(kInitialTypeSetCapacity);
    }

    using TimePoint = std::chrono::high_resolution_clock::time_point;

    // Per-thread local state for parallel dump.
    struct WorkerState {
        MemoryBuffer buffer;
        std::queue<ObjHeader*> queue;  // safety net, expect empty
#ifdef ENABLE_CRT
        std::vector<uint8_t> dataBuffer;  // thread-local buffer for CRT RefField cleaning
#endif
    };

    // Stages 1-2: global roots and thread roots. Returns end timestamp.
    TimePoint DumpRootsAndThreads(TimePoint startTime) {
        using Clock = std::chrono::high_resolution_clock;
        // Stage 1: global roots.
        int globalRootCount = 0;
        for (auto value : mm::GlobalRootSet()) {
            DumpTransitively(value);
            ++globalRootCount;
        }
        auto t1 = Clock::now();
        RuntimeLogInfo({kTagMemDump},
            "  dump/global_roots: %.2f ms, roots=%d, buffer=%zu MB",
            std::chrono::duration<double, std::milli>(t1 - startTime).count(),
            globalRootCount, memoryBuffer_.Size() / kBytesPerMB);

        // Stage 2: threads and thread roots.
        int threadCount = 0;
        int threadRootCount = 0;
        for (auto& thread : mm::GlobalData::Instance().threadRegistry().LockForIter()) {
            DumpThread(thread);
            ++threadCount;
            for (auto value : mm::ThreadRootSet(thread)) {
                DumpTransitively(thread, value);
                ++threadRootCount;
            }
        }
        auto t2 = Clock::now();
        RuntimeLogInfo({kTagMemDump},
            "  dump/thread_roots: %.2f ms, threads=%d, roots=%d, buffer=%zu MB",
            std::chrono::duration<double, std::milli>(t2 - t1).count(),
            threadCount, threadRootCount, memoryBuffer_.Size() / kBytesPerMB);
        return t2;
    }

    // CMS path: dump heap objects (parallel) + extra objects. Returns end timestamp.
    TimePoint DumpHeapAndExtraObjectsCMS(TimePoint t3) {
        using Clock = std::chrono::high_resolution_clock;
        auto timing = DumpHeapObjectsParallel();
        auto t4 = Clock::now();
        RuntimeLogInfo({kTagMemDump},
            "  dump/heap_objects: %.2f ms, objects=%zu, "
            "scan=%.2f ms, parallel=%.2f ms, merge=%.2f ms, buffer=%zu MB",
            std::chrono::duration<double, std::milli>(t4 - t3).count(),
            timing.objectCount,
            timing.scanMs, timing.parallelMs, timing.mergeMs,
            memoryBuffer_.Size() / kBytesPerMB);

        int extraObjCount = 0;
        GlobalData::Instance().allocator().TraverseAllocatedExtraObjects([&](auto extraObj) {
            DumpTransitively(extraObj);
            ++extraObjCount;
        });
        auto t5 = Clock::now();
        RuntimeLogInfo({kTagMemDump},
            "  dump/extra_objects: %.2f ms, count=%d, buffer=%zu MB",
            std::chrono::duration<double, std::milli>(t5 - t4).count(),
            extraObjCount, memoryBuffer_.Size() / kBytesPerMB);
        return t5;
    }

    // Stages 3-4: heap objects and extra objects. Returns end timestamp.
    TimePoint DumpHeapAndExtraObjects(TimePoint t3) {
#ifdef ENABLE_CRT
        auto t5 = t3;
        checkUseCRT<CheckMode::Slow>([&] {
            t5 = DumpHeapAndExtraObjectsCRT(t3);
        }, [&] {
            t5 = DumpHeapAndExtraObjectsCMS(t3);
        });
        return t5;
#else
        return DumpHeapAndExtraObjectsCMS(t3);
#endif
    }

#ifdef ENABLE_CRT
    // CRT path: parallel dump similar to CMS.
    TimePoint DumpHeapAndExtraObjectsCRT(TimePoint t3) {
        using Clock = std::chrono::high_resolution_clock;

        // Stage 3a: Collect all object pointers via ForEachObject.
        std::vector<ObjHeader*> allObjects;
        allObjects.reserve(kInitialObjectSetCapacity);
        auto scanStart = Clock::now();
        common::Heap::GetHeap().ForEachObject([&](common::BaseObject* baseObj) {
            auto* obj = reinterpret_cast<ObjHeader*>(baseObj);
            allObjects.push_back(obj);
            // Pre-dump types to avoid contention on dumpedTypes_ during parallel phase.
            DumpTransitively(obj->type_info());
            dumpedObjs_.Insert(obj);
        }, false);
        auto scanEnd = Clock::now();
        double scanMs = std::chrono::duration<double, std::milli>(scanEnd - scanStart).count();
        size_t totalObjects = allObjects.size();

        RuntimeLogInfo({kTagMemDump},
            "  dump/crt_scan: %.2f ms, objects=%zu, buffer=%zu MB",
            scanMs, totalObjects, memoryBuffer_.Size() / kBytesPerMB);

        // Fallback to single-threaded for tiny heaps.
        size_t numThreads = DetermineThreadCount();
        if (totalObjects < kMinParallelObjects || numThreads < kMinThreads) {
            auto singleStart = Clock::now();
            for (auto obj : allObjects) {
                DumpObjectOrArray(obj);
                EnqueuePermanentRefs(obj);
            }
            auto singleEnd = Clock::now();
            auto t5 = Clock::now();
            RuntimeLogInfo({kTagMemDump},
                "  dump/crt_single: %.2f ms, objects=%zu, buffer=%zu MB",
                std::chrono::duration<double, std::milli>(singleEnd - singleStart).count(),
                totalObjects, memoryBuffer_.Size() / kBytesPerMB);
            return t5;
        }

        // Stage 3b: Parallel dispatch + merge.
        // Estimate: typical object dump ~80 bytes (more than CMS due to RefField cleaning).
        constexpr size_t kEstimatedBytesPerObject = 80;
        auto result = DispatchAndMergeWorkers(allObjects, totalObjects,
                kEstimatedBytesPerObject,
                [this](const std::vector<ObjHeader*>& objs, size_t start, size_t end, WorkerState& ws) {
                    ProcessCRTObjectChunk(objs, start, end, ws);
                },
                [](WorkerState& ws, size_t estimatedPerWorker) {
                    // Pre-allocate per-thread dataBuffer for CRT RefField cleaning.
                    ws.dataBuffer.reserve(estimatedPerWorker / 2);
                });

        MaybeFlush();
        auto t5 = Clock::now();
        RuntimeLogInfo({kTagMemDump},
            "  dump/crt_parallel: threads=%zu, objects=%zu, chunksize=%.1f K, "
            "scan=%.2f ms, parallel=%.2f ms, merge=%.2f ms, "
            "queued=%zu, buffer=%zu MB",
            result.numThreads, totalObjects, result.chunkSize / kBytesPerKBDouble,
            scanMs, result.parallelMs, result.mergeMs,
            result.mergedQueued, result.mergedBytes / kBytesPerMB);
        return t5;
    }

    // Process a chunk of objects in CRT mode (parallel worker).
    void ProcessCRTObjectChunk(const std::vector<ObjHeader*>& allObjects,
                               size_t start, size_t end, WorkerState& ws) {
        for (size_t j = start; j < end; ++j) {
            // Prefetch next object's header to hide memory latency.
            if (j + 1 < end) {
                __builtin_prefetch(static_cast<const void*>(allObjects[j + 1]), 0, 1);
            }
            if (j + 2 < end) {
                __builtin_prefetch(static_cast<const void*>(allObjects[j + 2]), 0, 0);
            }
            ObjHeader* obj = allObjects[j];
            DumpObjectOrArrayCRT(obj, &ws.buffer, ws.dataBuffer);
            EnqueuePermanentRefsParallel(obj, ws.queue);
        }
    }

    // Dump object or array in CRT mode with thread-local buffer.
    void DumpObjectOrArrayCRT(ObjHeader* obj, MemoryBuffer* buf, std::vector<uint8_t>& dataBuffer) {
        const TypeInfo* type = obj->type_info();
        if (type->IsArray()) {
            DumpArrayCRT(type, obj->array(), buf, dataBuffer);
        } else {
            DumpObjectCRT(type, obj, buf, dataBuffer);
        }
    }

    // Dump object in CRT mode with thread-local dataBuffer.
    void DumpObjectCRT(const TypeInfo* type, ObjHeader* obj, MemoryBuffer* buf, std::vector<uint8_t>& dataBuffer) {
        DumpU8(TAG_OBJECT, buf);
        DumpId(obj, buf);
        DumpId(type, buf);

        size_t size = type->instanceSize_;
        size_t dataOffset = sizeof(TypeInfo*);
        size_t dataSize = size - dataOffset;
        uint8_t* data = reinterpret_cast<uint8_t*>(obj) + dataOffset;

        // Resize thread-local buffer if needed.
        if (dataBuffer.size() < dataSize) {
            dataBuffer.resize(dataSize);
        }
        std::copy(data, data + dataSize, dataBuffer.data());

        // Clean RefField pointers.
        for (int32_t i = 0; i < type->objOffsetsCount_; i++) {
            size_t fieldOffset = type->objOffsets_[i] - dataOffset;
            if (fieldOffset < dataSize) {
                CleanRefFieldInPlace(reinterpret_cast<uintptr_t*>(dataBuffer.data() + fieldOffset));
            }
        }

        DumpU32(dataSize, buf);
        DumpSpan(std_support::span<uint8_t>(dataBuffer.data(), dataSize), buf);
    }

    // Dump array in CRT mode with thread-local dataBuffer.
    void DumpArrayCRT(const TypeInfo* type, ArrayHeader* arr, MemoryBuffer* buf, std::vector<uint8_t>& dataBuffer) {
        DumpU8(TAG_ARRAY, buf);
        DumpId(arr, buf);
        DumpId(type, buf);

        uint32_t count = arr->count_;
        DumpU32(count, buf);

        int32_t elementSize = -type->instanceSize_;
        size_t dataOffset = alignUp(sizeof(ArrayHeader), elementSize);

        if (isStrip_ && (type != theArrayTypeInfo && type != theNativePtrArrayTypeInfo)) {
            DumpU32(0, buf);
        } else {
            size_t dataSize = elementSize * count;
            DumpU32(dataSize, buf);

            uint8_t* data = reinterpret_cast<uint8_t*>(arr) + dataOffset;

            // Resize thread-local buffer if needed.
            if (dataBuffer.size() < dataSize) {
                dataBuffer.resize(dataSize);
            }
            std::copy(data, data + dataSize, dataBuffer.data());

            // Clean RefField pointers for each element.
            for (uint32_t i = 0; i < count; i++) {
                CleanRefFieldInPlace(reinterpret_cast<uintptr_t*>(dataBuffer.data() + i * elementSize));
            }

            DumpSpan(std_support::span<uint8_t>(dataBuffer.data(), dataSize), buf);
        }
    }

    // Enqueue permanent refs in parallel (thread-safe).
    void EnqueuePermanentRefsParallel(ObjHeader* obj, std::queue<ObjHeader*>& queue) {
        auto enqueueRef = [&](ObjHeader* refObj) {
            if (isNullOrMarker(refObj)) return;
            common::RefField<false> refField(reinterpret_cast<uintptr_t>(refObj));
            refObj = reinterpret_cast<ObjHeader*>(refField.GetTargetObject());
            if (refObj == nullptr) return;
            if (common::Heap::IsHeapAddress(reinterpret_cast<void*>(refObj))) return;
            if (!refObj->permanent()) return;
            queue.push(refObj);
        };

        const TypeInfo* type = obj->type_info();
        if (type == theArrayTypeInfo) {
            ArrayHeader* arr = obj->array();
            int32_t elementSize = -type->instanceSize_;
            size_t dataOffset = alignUp(sizeof(ArrayHeader), elementSize);
            uint8_t* data = reinterpret_cast<uint8_t*>(arr) + dataOffset;
            for (uint32_t i = 0; i < arr->count_; i++) {
                ObjHeader* refObj = *reinterpret_cast<ObjHeader**>(data + i * sizeof(uintptr_t));
                enqueueRef(refObj);
            }
        } else {
            for (int32_t i = 0; i < type->objOffsetsCount_; i++) {
                auto offset = reinterpret_cast<uintptr_t>(obj) + type->objOffsets_[i];
                ObjHeader* refObj = *reinterpret_cast<ObjHeader**>(offset);
                enqueueRef(refObj);
            }
        }
    }
#endif

    // Stages 5-6: stable references and enqueued objects. Returns end timestamp.
    TimePoint DumpFinalStages(TimePoint t5) {
        using Clock = std::chrono::high_resolution_clock;
        DumpStableRefs();
        auto t6 = Clock::now();
        RuntimeLogInfo({kTagMemDump},
            "  dump/stable_refs: %.2f ms, buffer=%zu MB",
            std::chrono::duration<double, std::milli>(t6 - t5).count(),
            memoryBuffer_.Size() / kBytesPerMB);

        int queuedCount = static_cast<int>(objQueue_.size());
        DumpEnqueuedObjects();
        auto t7 = Clock::now();
        RuntimeLogInfo({kTagMemDump},
            "  dump/enqueued: %.2f ms, queued=%d, dumped_objs=%zu, dumped_types=%zu, buffer=%zu MB",
            std::chrono::duration<double, std::milli>(t7 - t6).count(),
            queuedCount, dumpedObjs_.Size(), dumpedTypes_.Size(),
            memoryBuffer_.Size() / kBytesPerMB);
        return t7;
    }

    // Dumps the memory into the internal buffer (no compression yet).
    void Dump() {
        using Clock = std::chrono::high_resolution_clock;
        auto totalStart = Clock::now();

#ifdef ENABLE_CRT
        RuntimeLogInfo({kTagMemDump}, "Starting memory dump (CRT mode).");
#else
        RuntimeLogInfo({kTagMemDump}, "Starting memory dump (CMS mode).");
#endif

        DumpStr("Kotlin/Native dump 1.0.10");
        DumpBool(konan::isLittleEndian());
        DumpU8(sizeof(void*));
        auto t1 = Clock::now();
        RuntimeLogInfo({kTagMemDump},
            "  dump/header: %.2f ms",
            std::chrono::duration<double, std::milli>(t1 - totalStart).count());

        auto t3 = DumpRootsAndThreads(t1);
        MaybeFlush();
        auto t5 = DumpHeapAndExtraObjects(t3);
        MaybeFlush();
        auto t7 = DumpFinalStages(t5);
        MaybeFlush();

        double totalMs = std::chrono::duration<double, std::milli>(t7 - totalStart).count();
        RuntimeLogInfo({kTagMemDump}, "  dump/total: %.2f ms", totalMs);
    }

    // Compresses the collected dump data into the given file descriptor.
    void CompressToFile(int fd) {
        memoryBuffer_.CompressToFile(fd);
    }

    // Writes the collected raw dump data directly to fd (no compression).
    // Throws std::system_error on write failure.
    void WriteRawToFd(int fd) {
        const char* data = memoryBuffer_.Data();
        size_t size = memoryBuffer_.Size();
        const char* ptr = data;
        while (size > 0) {
            ssize_t written = write(fd, ptr, size);
            if (written < 0) {
                if (errno == EINTR) { continue; }
                throw std::system_error(errno, std::generic_category());
            }
            ptr += written;
            size -= static_cast<size_t>(written);
        }
    }

    // Returns the size of the raw dump data in bytes.
    size_t GetDumpSize() const { return totalDumpSize_ + memoryBuffer_.Size(); }

    // Flush any remaining buffered data to streamFd_ (streaming mode only).
    void FlushRemaining() {
        if (streamFd_ >= 0 && memoryBuffer_.Size() > 0) {
            totalDumpSize_ += memoryBuffer_.FlushToFd(streamFd_);
        }
    }

private:
    // ---- Low-level write helpers ----
    // All take an optional MemoryBuffer*; when nullptr, write to memoryBuffer_.

    template <typename T>
    void DumpSpan(std_support::span<T> span, MemoryBuffer* buf = nullptr) {
        MemoryBuffer& b = buf ? *buf : memoryBuffer_;
        b.Write(span.data(), span.size() * sizeof(T));
    }

    template <typename T>
    void DumpValue(T value, MemoryBuffer* buf = nullptr) {
        DumpSpan(std_support::span<T>(&value, 1), buf);
    }

    void DumpId(const void* ptr, MemoryBuffer* buf = nullptr) { DumpValue(ptr, buf); }

    void DumpBool(bool b, MemoryBuffer* buf = nullptr) { DumpU8(b ? 1 : 0, buf); }

    void DumpU8(uint8_t i, MemoryBuffer* buf = nullptr) { DumpValue(i, buf); }

    void DumpU32(uint32_t i, MemoryBuffer* buf = nullptr) { DumpValue(i, buf); }

    void DumpStr(const char* str, MemoryBuffer* buf = nullptr) {
        DumpSpan(std_support::span<const char>(str, strlen(str) + 1), buf);
    }

    void DumpString(ObjHeader* obj, MemoryBuffer* buf = nullptr) {
        char* str = CreateCStringFromString(obj);
        DumpStr(str, buf);
        DisposeCString(str);
    }

    void DumpStringOrEmptyIfNull(ObjHeader* obj, MemoryBuffer* buf = nullptr) {
        if (obj) {
            DumpString(obj, buf);
        } else {
            DumpStr("", buf);
        }
    }

    void DumpThread(ThreadData& thread) {
        RuntimeLogDebug({kTagMemDump}, "Dumping thread %" PRIuPTR, thread.threadId());
        DumpU8(TAG_THREAD);
        DumpId(&thread);
    }

    void DumpGlobalRoot(GlobalRootSet::Value& value) {
        RuntimeLogDebug({kTagMemDump}, "Dumping global root: source=%d, object=%p", static_cast<int>(value.source), value.object);
        DumpU8(TAG_GLOBAL_ROOT);
        DumpU8(UInt8(value.source));
        DumpId(value.object);
    }

    void DumpThreadRoot(ThreadData& thread, ThreadRootSet::Value& value) {
        RuntimeLogDebug({kTagMemDump}, "Dumping thread root: source=%d, object=%p", static_cast<int>(value.source), value.object);
        DumpU8(TAG_THREAD_ROOT);
        DumpId(&thread);
        DumpU8(UInt8(value.source));
        DumpId(value.object);
    }

#ifdef ENABLE_CRT
    // Decodes the tagged pointer stored at `fieldPtr` via RefField, writes the clean pointer back in place
    // and nulls it out if it references an already-freed non-movable object.
    static void CleanRefFieldInPlace(uintptr_t* fieldPtr) {
        auto* refField = reinterpret_cast<common::RefField<false>*>(fieldPtr);
        uintptr_t cleanValue = reinterpret_cast<uintptr_t>(refField->GetTargetObject());
        if (cleanValue > 1 && common::Heap::IsHeapAddress(cleanValue)) {
            auto* refRegion = common::RegionDesc::GetRegionDescAt(cleanValue);
            if (refRegion->IsMonoSizeNonMovableRegion() &&
                refRegion->IsFreeNonMovableObject(reinterpret_cast<common::BaseObject*>(cleanValue))) {
                cleanValue = 0; // points at a freed object, treat as null
            }
        }
        *fieldPtr = cleanValue;
    }
#endif

    void DumpObject(const TypeInfo* type, ObjHeader* obj, MemoryBuffer* buf = nullptr) {
        RuntimeLogDebug({kTagMemDump}, "Dumping object %p of type %s", obj, type->fqName().c_str());
        DumpU8(TAG_OBJECT, buf);
        DumpId(obj, buf);
        DumpId(type, buf);

        size_t size = type->instanceSize_;
        size_t dataOffset = sizeof(TypeInfo*);
        size_t dataSize = size - dataOffset;
        uint8_t* data = reinterpret_cast<uint8_t*>(obj) + dataOffset;

        // For CRT mode, use RefField::GetTargetObject() to get clean pointers (without high 16 bits)
#ifdef ENABLE_CRT
        checkUseCRT<CheckMode::Slow>([&] {
            // Resize buffer if needed and copy object data
            if (dataBuffer_.size() < dataSize) {
                dataBuffer_.resize(dataSize);
            }
            std::copy(data, data + dataSize, dataBuffer_.data());

            // use RefField::GetTargetObject() to get clean pointers from each reference field
            for (int32_t i = 0; i < type->objOffsetsCount_; i++) {
                size_t fieldOffset = type->objOffsets_[i] - dataOffset;
                if (fieldOffset < dataSize) {
                    CleanRefFieldInPlace(reinterpret_cast<uintptr_t*>(dataBuffer_.data() + fieldOffset));
                }
            }
            
            DumpU32(dataSize, buf);
            DumpSpan(std_support::span<uint8_t>(dataBuffer_.data(), dataSize), buf);
        }, [&] {
            // CMS mode: write raw data directly
            DumpU32(dataSize, buf);
            DumpSpan(std_support::span<uint8_t>(data, dataSize), buf);
        });
#else
        DumpU32(dataSize, buf);
        DumpSpan(std_support::span<uint8_t>(data, dataSize), buf);
#endif
    }

    void DumpArray(const TypeInfo* type, ArrayHeader* arr, MemoryBuffer* buf = nullptr) {
        RuntimeLogDebug({kTagMemDump}, "Dumping array %p", arr);
        DumpU8(TAG_ARRAY, buf);
        DumpId(arr, buf);
        DumpId(type, buf);

        uint32_t count = arr->count_;
        DumpU32(count, buf);

        int32_t elementSize = -type->instanceSize_;
        size_t dataOffset = alignUp(sizeof(ArrayHeader), elementSize);

        if (isStrip_ && (type != theArrayTypeInfo && type != theNativePtrArrayTypeInfo)) {
            DumpU32(0, buf);
        } else {
            // theArrayTypeInfo / theNativePtrArrayTypeInfo array: write raw data.
            size_t dataSize = elementSize * count;
            DumpU32(dataSize, buf);

            uint8_t* data = reinterpret_cast<uint8_t*>(arr) + dataOffset;
#ifdef ENABLE_CRT
            checkUseCRT<CheckMode::Slow>([&] {
                // Copy data to reusable buffer and clean RefField pointers.
                if (dataBuffer_.size() < dataSize) {
                    dataBuffer_.resize(dataSize);
                }
                std::copy(data, data + dataSize, dataBuffer_.data());
                for (uint32_t i = 0; i < count; i++) {
                    CleanRefFieldInPlace(reinterpret_cast<uintptr_t*>(dataBuffer_.data() + i * elementSize));
                }
                DumpSpan(std_support::span<uint8_t>(dataBuffer_.data(), dataSize), buf);
            }, [&] {
                // CMS mode: write raw data directly
                DumpSpan(std_support::span<uint8_t>(data, dataSize), buf);
            });
#else
            DumpSpan(std_support::span<uint8_t>(data, dataSize), buf);
#endif
        }
    }

    void DumpObjectOrArray(ObjHeader* obj, MemoryBuffer* buf = nullptr) {
        const TypeInfo* type = obj->type_info();
        if (type->IsArray()) {
            DumpArray(type, obj->array(), buf);
        } else {
            DumpObject(type, obj, buf);
        }
    }

    void EnqueuePermanentRefs(ObjHeader* obj) {
#ifdef ENABLE_CRT
        checkUseCRT<CheckMode::Slow>([&] {
            auto enqueueRef = [&](ObjHeader* refObj) {
                // Skip null or marker values
                if (isNullOrMarker(refObj)) return;
                // use RefField::GetTargetObject() to get clean pointers
                common::RefField<false> refField(reinterpret_cast<uintptr_t>(refObj));
                refObj = reinterpret_cast<ObjHeader*>(refField.GetTargetObject());
                // Skip null
                if (refObj == nullptr) return;
                // Skip heap addresses - they will be processed normally through the heap traversal
                if (common::Heap::IsHeapAddress(reinterpret_cast<void*>(refObj))) return;
                // check if it's a valid permanent.
                if (!refObj->permanent()) return;
                Enqueue(refObj);
            };

            const TypeInfo* type = obj->type_info();
            if (type == theArrayTypeInfo) {
                ArrayHeader* arr = obj->array();
                int32_t elementSize = -type->instanceSize_;
                size_t dataOffset = alignUp(sizeof(ArrayHeader), elementSize);
                uint8_t* data = reinterpret_cast<uint8_t*>(arr) + dataOffset;
                for (uint32_t i = 0; i < arr->count_; i++) {
                    ObjHeader* refObj = *reinterpret_cast<ObjHeader**>(data + i * sizeof(uintptr_t));
                    enqueueRef(refObj);
                }
            } else {
                for (int32_t i = 0; i < type->objOffsetsCount_; i++) {
                    auto offset = reinterpret_cast<uintptr_t>(obj) + type->objOffsets_[i];
                    ObjHeader* refObj = *reinterpret_cast<ObjHeader**>(offset);
                    enqueueRef(refObj);
                }
            }
        });
#endif
    }

    void DumpType(const TypeInfo* type, MemoryBuffer* buf = nullptr) {
        RuntimeLogDebug({kTagMemDump}, "Dumping type %s", type->fqName().c_str());
        DumpU8(TAG_TYPE, buf);
        DumpId(type, buf);

        bool isArray = type->IsArray();
        bool isExtended = type->extendedInfo_ != nullptr;
        bool isObjectArray = type == theArrayTypeInfo;
        uint8_t flags =
                (isArray ? TYPE_FLAG_ARRAY : 0) | (isExtended ? TYPE_FLAG_EXTENDED : 0) | (isObjectArray ? TYPE_FLAG_OBJECT_ARRAY : 0);
        DumpU8(flags, buf);

        DumpId(type->superType_, buf);

        DumpStringOrEmptyIfNull(type->packageName_, buf);
        DumpStringOrEmptyIfNull(type->relativeName_, buf);

        if (type->IsArray()) {
            DumpArrayInfo(type, buf);
        } else {
            DumpObjectInfo(type, buf);
        }
    }

    void DumpArrayInfo(const TypeInfo* type, MemoryBuffer* buf = nullptr) {
        int32_t elementSize = -type->instanceSize_;
        DumpU32(elementSize, buf);

        if (type->extendedInfo_ != nullptr) {
            DumpArrayInfo(type->extendedInfo_, buf);
        }
    }

    void DumpArrayInfo(const ExtendedTypeInfo* extendedInfo, MemoryBuffer* buf = nullptr) {
        uint8_t elementType = -extendedInfo->fieldsCount_;
        DumpU8(elementType, buf);
    }

    void DumpObjectInfo(const TypeInfo* type, MemoryBuffer* buf = nullptr) {
        size_t dataOffset = sizeof(TypeInfo*);

        DumpU32(type->instanceSize_ - dataOffset, buf);
        DumpOffsets(type, dataOffset, buf);

        if (type->extendedInfo_ != nullptr) {
            DumpObjectInfo(type->extendedInfo_, dataOffset, buf);
        }
    }

    void DumpOffsets(const TypeInfo* type, size_t dataOffset, MemoryBuffer* buf = nullptr) {
        int32_t count = type->objOffsetsCount_;
        DumpU32(count, buf);
        for (int32_t i = 0; i < count; i++) {
            DumpU32(type->objOffsets_[i] - dataOffset, buf);
        }
    }

    void DumpObjectInfo(const ExtendedTypeInfo* extendedInfo, size_t dataOffset, MemoryBuffer* buf = nullptr) {
        int32_t fieldsCount = extendedInfo->fieldsCount_;
        DumpU32(fieldsCount, buf);
        for (int32_t i = 0; i < fieldsCount; i++) {
            DumpU32(extendedInfo->fieldOffsets_[i] - dataOffset, buf);
            DumpU8(extendedInfo->fieldTypes_[i], buf);
            DumpStr(extendedInfo->fieldNames_[i], buf);
        }
    }

    void DumpTransitively(const TypeInfo* type, MemoryBuffer* buf = nullptr) {
        if (dumpedTypes_.Insert(type)) {
            // Dump super-type recursively, as the depth is not going to be a problem.
            if (type->superType_ != nullptr) {
                DumpTransitively(type->superType_, buf);
            }

            DumpType(type, buf);
        }
    }

    void DumpTransitively(ObjHeader* obj) {
        if (dumpedObjs_.Insert(obj)) {
            DumpTransitively(obj->type_info());

            DumpObjectOrArray(obj);

            checkUseCRT<CheckMode::Slow>([&] {
                EnqueuePermanentRefs(obj);
            }, [&] {
                traverseReferredObjects(obj, [&](auto refObj) { Enqueue(refObj); });
            });
        }
    }

    void DumpTransitively(ExtraObjectData* extraObj) {
        RuntimeLogDebug({kTagMemDump}, "Dumping extra object %p", extraObj);
        DumpU8(TAG_EXTRA_OBJECT);
        DumpId(extraObj);

        ObjHeader* baseObj = nullptr;
        checkUseCRT<CheckMode::Slow>([&] {
            extraObj->forEachRefField([&](ObjHeader* ref) {
                baseObj = ref;
            });
        }, [&] {
            baseObj = extraObj->GetBaseObject();
        });
        DumpId(baseObj);

        if (!isNullOrMarker(baseObj)) {
            Enqueue(baseObj);
        }

        void* associatedObject =
#if defined(KONAN_OBJC_INTEROP) || defined(KONAN_OHOS)
                extraObj->AssociatedObject();
#else
                nullptr;
#endif
        DumpId(associatedObject);
    }

    void DumpTransitively(GlobalRootSet::Value& value) {
        ObjHeader* obj = value.object;
        if (isNullOrMarker(obj)) {
            return;
        }

        DumpGlobalRoot(value);

        Enqueue(obj);
    }

    void DumpTransitively(ThreadData& thread, ThreadRootSet::Value& value) {
        ObjHeader* obj = value.object;
        if (isNullOrMarker(obj)) {
            return;
        }

        DumpThreadRoot(thread, value);

        Enqueue(obj);
    }

    void Enqueue(ObjHeader* obj) { objQueue_.push(obj); }

    void DumpEnqueuedObjects() {
        while (!objQueue_.empty()) {
            auto obj = objQueue_.front();
            objQueue_.pop();
            DumpTransitively(obj);
        }
    }

    // ---- Parallel heap-object dump ----
    struct HeapDumpTiming {
        size_t objectCount;
        double scanMs;     // combined collect + type-dump + prefill
        double parallelMs;
        double mergeMs;
    };

    // WorkerState is defined earlier in the class to be accessible by both CMS and CRT functions.

    // Threshold for single-threaded fallback on tiny heaps.
    static constexpr size_t kMinParallelObjects = 10000;

    // Determine thread count clamped to [kMinThreads, kMaxConcurrency].
    static size_t DetermineThreadCount() {
        size_t numThreads = std::max(kMinThreads, static_cast<size_t>(std::thread::hardware_concurrency()));
        if (numThreads > kMaxConcurrency) { numThreads = kMaxConcurrency; }
        return numThreads;
    }

    // Result of a parallel dispatch + merge operation.
    struct DispatchResult {
        size_t numThreads;
        size_t chunkSize;
        double dispatchMs;
        double parallelMs;   // thread run time (join - dispatch)
        double mergeMs;
        size_t mergedBytes;
        size_t mergedQueued;
    };

    // Shared parallel dispatch: sets up workers, spawns threads, waits, merges.
    // workerFn:  void(const std::vector<ObjHeader*>&, size_t start, size_t end, WorkerState&)
    // setupFn:   void(WorkerState&, size_t estimatedPerWorker)
    template <typename WorkerFn, typename SetupFn>
    DispatchResult DispatchAndMergeWorkers(
            const std::vector<ObjHeader*>& allObjects,
            size_t totalObjects,
            size_t estimatedBytesPerObject,
            WorkerFn workerFn,
            SetupFn setupFn) {
        using Clock = std::chrono::high_resolution_clock;

        size_t numThreads = DetermineThreadCount();
        if (numThreads == 0) { return {0, 0, 0.0, 0.0, 0.0, 0, 0}; }

        size_t chunkSize = (totalObjects + numThreads - 1) / numThreads;
        std::vector<WorkerState> workers(numThreads);
        size_t estimatedPerWorker =
                (totalObjects * estimatedBytesPerObject) / numThreads + kBytesPerMB;
        for (auto& w : workers) {
            w.buffer.Reserve(estimatedPerWorker);
            setupFn(w, estimatedPerWorker);
        }

        std::vector<std::thread> threads;
        threads.reserve(numThreads);
        auto dispatchStart = Clock::now();
        for (size_t i = 0; i < numThreads; ++i) {
            size_t start = i * chunkSize;
            size_t end = std::min(start + chunkSize, totalObjects);
            if (start >= end) { break; }
            threads.emplace_back([this, &allObjects, &workers, &workerFn, start, end, i]() {
                workerFn(allObjects, start, end, workers[i]);
            });
        }
        auto dispatchEnd = Clock::now();

        for (auto& t : threads) { t.join(); }
        auto joinEnd = Clock::now();

        auto mergeStart = Clock::now();
        auto [mergedBytes, mergedQueued] = MergeWorkerBuffers(workers);
        auto mergeEnd = Clock::now();

        return {numThreads, chunkSize,
                std::chrono::duration<double, std::milli>(dispatchEnd - dispatchStart).count(),
                std::chrono::duration<double, std::milli>(joinEnd - dispatchEnd).count(),
                std::chrono::duration<double, std::milli>(mergeEnd - mergeStart).count(),
                mergedBytes, mergedQueued};
    }

    // Single-pass scan: collects object pointers, pre-dumps types,
    // and populates the global object set. Returns scan duration in ms.
    double ScanHeapObjects(std::vector<ObjHeader*>& allObjects) {
        using Clock = std::chrono::high_resolution_clock;
        auto t0 = Clock::now();
        allObjects.reserve(kInitialObjectSetCapacity);
        GlobalData::Instance().allocator().TraverseAllocatedObjects([&](auto obj) {
            allObjects.push_back(obj);
            DumpTransitively(obj->type_info());
            dumpedObjs_.Insert(obj);
        });
        auto t1 = Clock::now();
        return std::chrono::duration<double, std::milli>(t1 - t0).count();
    }

    // Single-threaded fallback for tiny heaps. Returns dump duration in ms.
    double DumpHeapSingleThreaded(const std::vector<ObjHeader*>& allObjects) {
        using Clock = std::chrono::high_resolution_clock;
        auto t0 = Clock::now();
        size_t n = allObjects.size();
        for (size_t j = 0; j < n; ++j) {
            if (j + 1 < n) {
                __builtin_prefetch(static_cast<const void*>(allObjects[j + 1]), 0, 1);
            }
            ObjHeader* obj = allObjects[j];
            DumpObjectOrArray(obj);
            traverseReferredObjects(obj, [this](auto refObj) {
                if (!dumpedObjs_.Contains(refObj)) { Enqueue(refObj); }
            });
        }
        auto t1 = Clock::now();
        return std::chrono::duration<double, std::milli>(t1 - t0).count();
    }

    // Worker body: dumps objects [start, end) from allObjects into ws.buffer.
    void ProcessObjectChunk(const std::vector<ObjHeader*>& allObjects,
                            size_t start, size_t end, WorkerState& ws) {
        for (size_t j = start; j < end; ++j) {
            // Prefetch next object's header to hide memory latency.
            // Objects are accessed in random order (by pointer), so
            // prefetching 1-2 ahead overlaps memory fetch with current work.
            if (j + 1 < end) {
                __builtin_prefetch(static_cast<const void*>(allObjects[j + 1]), 0, 1);
            }
            if (j + 2 < end) {
                __builtin_prefetch(static_cast<const void*>(allObjects[j + 2]), 0, 0);
            }
            ObjHeader* obj = allObjects[j];
            DumpObjectOrArray(obj, &ws.buffer);
            traverseReferredObjects(obj, [this, &ws](auto refObj) {
                // Filter sentinel values: traverseReferredObjects
                // may expose null (0) or marker (1) pointers on
                // some platforms.  Skip them to avoid false enqueues
                // and potential crashes during queue processing.
                if (!isNullOrMarker(refObj) && !dumpedObjs_.Contains(refObj)) {
                    ws.queue.push(refObj);
                }
            });
        }
    }

    // Merges per-thread buffers into the global buffer and transfers
    // leftover queue entries. Returns {mergedBytes, mergedQueued}.
    // In streaming mode (streamFd_ >= 0), writes worker buffers directly
    // to fd, skipping the merge copy (~400 MB saved).
    std::pair<size_t, size_t> MergeWorkerBuffers(std::vector<WorkerState>& workers) {
        size_t mergedBytes = 0;
        size_t mergedQueued = 0;
        for (auto& ws : workers) {
            mergedBytes += ws.buffer.Size();
        }
        if (streamFd_ >= 0) {
            // Streaming: write directly to fd, no merge copy.
            for (auto& ws : workers) {
                totalDumpSize_ += ws.buffer.FlushToFd(streamFd_);
                while (!ws.queue.empty()) {
                    objQueue_.push(ws.queue.front());
                    ws.queue.pop();
                    ++mergedQueued;
                }
            }
        } else {
            // Non-streaming: merge into memoryBuffer_ for later compression.
            memoryBuffer_.Reserve(memoryBuffer_.Size() + mergedBytes);
            for (auto& ws : workers) {
                memoryBuffer_.Append(ws.buffer);
                while (!ws.queue.empty()) {
                    objQueue_.push(ws.queue.front());
                    ws.queue.pop();
                    ++mergedQueued;
                }
            }
        }
        return {mergedBytes, mergedQueued};
    }

    HeapDumpTiming DumpHeapObjectsParallel() {
        // --- Sub-stage 3a: combined scan ---
        std::vector<ObjHeader*> allObjects;
        double scanMs = ScanHeapObjects(allObjects);
        size_t totalObjects = allObjects.size();

        // Fallback to single-threaded for tiny heaps.
        size_t numThreads = DetermineThreadCount();
        if (totalObjects < kMinParallelObjects || numThreads < kMinThreads) {
            double parallelMs = DumpHeapSingleThreaded(allObjects);
            return {totalObjects, scanMs, parallelMs, 0.0};
        }

        // --- Sub-stage 3b: dispatch parallel workers ---
        // Estimate: typical object dump ~60 bytes.
        constexpr size_t kEstimatedBytesPerObject = 60;
        auto result = DispatchAndMergeWorkers(allObjects, totalObjects,
                kEstimatedBytesPerObject,
                [this](const std::vector<ObjHeader*>& objs, size_t start, size_t end, WorkerState& ws) {
                    ProcessObjectChunk(objs, start, end, ws);
                },
                [](WorkerState&, size_t) {});

        RuntimeLogInfo({kTagMemDump},
            "  dump/heap_parallel: threads=%zu, objects=%zu, chunksize=%.1f K, "
            "merged_queued=%zu, buffer=%zu MB",
            result.numThreads, totalObjects, result.chunkSize / kBytesPerKBDouble,
            result.mergedQueued, result.mergedBytes / kBytesPerMB);
        MaybeFlush();

        return {totalObjects, scanMs, result.parallelMs, result.mergeMs};
    }

    uint8_t UInt8(GlobalRootSet::Source source) {
        switch (source) {
            case GlobalRootSet::Source::kGlobal:
                return 1;
            case GlobalRootSet::Source::kStableRef:
                return 2;
        }
    }

    uint8_t UInt8(ThreadRootSet::Source source) {
        switch (source) {
            case ThreadRootSet::Source::kStack:
                return 1;
            case ThreadRootSet::Source::kTLS:
                return 2;
            case ThreadRootSet::Source::kHandle:
                return 3;
        }
    }

    void DumpStableRefs() {
        auto& registry = mm::ExternalRCRefRegistry::instance();
        auto iterable = registry.lockForIter();
        for (auto it = iterable.begin(); it != iterable.end(); ++it) {
            mm::ExternalRCRefImpl* ref = it.get();
            KRef obj = (*it).load(std::memory_order_relaxed);
            if (obj != nullptr) {
                RuntimeLogDebug({kTagMemDump}, "Dumping stable ref %p -> %p", ref, obj);
                DumpU8(TAG_STABLE_REF);
                DumpId(ref);
                DumpId(obj);

                Enqueue(obj);
            }
        }
    }

    const uint8_t TAG_TYPE = 0x01;
    const uint8_t TAG_OBJECT = 0x02;
    const uint8_t TAG_ARRAY = 0x03;
    const uint8_t TAG_EXTRA_OBJECT = 0x04;
    const uint8_t TAG_THREAD = 0x05;
    const uint8_t TAG_GLOBAL_ROOT = 0x06;
    const uint8_t TAG_THREAD_ROOT = 0x07;
    const uint8_t TAG_STABLE_REF = 0x08;

    const uint8_t TYPE_FLAG_ARRAY = 1 << 0;
    const uint8_t TYPE_FLAG_EXTENDED = 1 << 1;
    const uint8_t TYPE_FLAG_OBJECT_ARRAY = 1 << 2;

    // In-memory buffer that collects all raw dump data before compression.
    MemoryBuffer memoryBuffer_;

    // A set of already dumped type pointers.
    PointerSet dumpedTypes_;

    // A set of already dumped objects.
    PointerSet dumpedObjs_;

    // A queue of objects to dump transitively.
    std::queue<ObjHeader*> objQueue_;

    bool isStrip_ = false;

    // Streaming support: when streamFd_ >= 0, the buffer is flushed
    // to this fd periodically during Dump() to avoid holding the full
    // dump in memory. Used by the sync DumpMemory path.
    static constexpr size_t kFlushThreshold = 64 * 1024 * 1024;  // 64 MB
    int streamFd_ = -1;
    size_t totalDumpSize_ = 0;  // bytes already flushed to fd

    // Flush buffer to streamFd_ if it exceeds the threshold.
    void MaybeFlush() {
        if (streamFd_ >= 0 && memoryBuffer_.Size() >= kFlushThreshold) {
            totalDumpSize_ += memoryBuffer_.FlushToFd(streamFd_);
        }
    }

    // Reusable buffer for CRT mode to avoid per-object heap allocation
    // when copying object/array data for pointer cleaning.
    std::vector<uint8_t> dataBuffer_;
};

void PrepareForMemoryDump() {
    mm::GlobalData::Instance().threadRegistry().PublishAll();
}

void DumpMemoryOrThrow(int fd, bool isStrip) {
    using Clock = std::chrono::high_resolution_clock;

    // Phase 1: Dump memory into internal buffer (no compression).
    auto dumpStart = Clock::now();
    MemoryDumper dumper(isStrip);
    dumper.Dump();
    auto dumpEnd = Clock::now();

    // Phase 2: Compress collected data into fd using parallel threads.
    size_t numChunks = std::max(kMinThreads, static_cast<size_t>(std::thread::hardware_concurrency()));
    if (numChunks > kMaxConcurrency) { numChunks = kMaxConcurrency; }
    RuntimeLogInfo({kTagMemDump},
        "Starting parallel compression (%zu threads) of %zu bytes",
        numChunks, dumper.GetDumpSize());
    auto compressStart = Clock::now();
    dumper.CompressToFile(fd);
    auto compressEnd = Clock::now();

    // Record precise timing for each phase.
    double dumpMs = std::chrono::duration<double, std::milli>(dumpEnd - dumpStart).count();
    double compressMs = std::chrono::duration<double, std::milli>(compressEnd - compressStart).count();
    double totalMs = std::chrono::duration<double, std::milli>(compressEnd - dumpStart).count();

    RuntimeLogInfo({kTagMemDump},
        ">>> Timing: dump=%.2f ms, compress=%.2f ms, total=%.2f ms, raw_size=%zu bytes",
        dumpMs, compressMs, totalMs, dumper.GetDumpSize());
}

bool DumpMemoryAsync(int fd, bool isStrip) noexcept {
    using Clock = std::chrono::high_resolution_clock;

#ifdef KONAN_OHOS
    // Check if a previous forked child is still running.
    // The parent's DumpGuard (atomic flag) is released right after fork,
    // so it cannot prevent concurrent children. We track the last child's
    // PID and use kill(pid, 0) to check if it's still alive.
    // SIGCHLD is set to SIG_IGN so children are auto-reaped (no zombies).
    static std::atomic<pid_t> sLastChildPid{0};
    static std::once_flag sSigchldInit;
    std::call_once(sSigchldInit, [] {
        signal(SIGCHLD, SIG_IGN);  // Auto-reap: no zombies after child exits.
    });

    pid_t lastPid = sLastChildPid.load();
    if (lastPid > 0) {
        if (kill(lastPid, 0) == 0) {
            // Previous child still running — skip this dump.
            RuntimeLogInfo({kTagMemDump},
                "Previous forked dump (pid=%d) still running, skipping.", lastPid);
            return false;
        }
        // Child finished (auto-reaped by SIG_IGN), clear PID.
        sLastChildPid.compare_exchange_strong(lastPid, 0);
    }
#endif

    auto prepStart = Clock::now();
    PrepareForMemoryDump();
    auto prepEnd = Clock::now();

    bool success = true;
    try {
#ifndef KONAN_OHOS
        DumpMemoryOrThrow(fd, isStrip);
#else
        RuntimeLogInfo({kTagMemDump}, "Attempting to fork process for memory dump.");
        auto forkStart = Clock::now();
        int pid = fork();
        auto forkEnd = Clock::now();
        if (pid < 0) {
            RuntimeLogError({kTagMemDump}, "Failed to fork process for memory dump.");
            return false;
        }
        if (pid == 0) {
            // Child process: do the actual dump+compress.
            RuntimeLogInfo({kTagMemDump},
                "  fork/child: fork=%.2f ms, prepare=%.2f ms",
                std::chrono::duration<double, std::milli>(forkEnd - forkStart).count(),
                std::chrono::duration<double, std::milli>(prepEnd - prepStart).count());
            DumpMemoryOrThrow(fd, isStrip);
            RuntimeLogInfo({kTagMemDump}, "Forked process memory dump done.");
            _exit(0);
        }
        // Parent: record child PID and return immediately.
        sLastChildPid.store(pid);
        RuntimeLogInfo({kTagMemDump},
            "  fork/parent: fork=%.2f ms, prepare=%.2f ms, child_pid=%d",
            std::chrono::duration<double, std::milli>(forkEnd - forkStart).count(),
            std::chrono::duration<double, std::milli>(prepEnd - prepStart).count(),
            pid);
#endif
    } catch (const std::system_error& e) {
        success = false;
        RuntimeLogError({kTagMemDump}, "Memory dump error: %s", e.what());
    }
    return success;
}

bool DumpMemory(int fd) noexcept {
    PrepareForMemoryDump();

    bool success = true;
    try {
        auto dumpStart = std::chrono::high_resolution_clock::now();
        // Streaming mode: flush buffer to fd during dump, avoiding
        // holding the full dump (~430 MB) in memory.
        MemoryDumper dumper(false, fd);
        dumper.Dump();
        dumper.FlushRemaining();
        auto dumpEnd = std::chrono::high_resolution_clock::now();

        double dumpMs = std::chrono::duration<double, std::milli>(dumpEnd - dumpStart).count();
        RuntimeLogInfo({kTagMemDump},
            ">>> DumpMemory: dump=%.2f ms, raw_size=%zu bytes (streamed)",
            dumpMs, dumper.GetDumpSize());
    } catch (const std::system_error& e) {
        success = false;
        RuntimeLogError({kTagMemDump}, "Memory dump error: %s", e.what());
    }
    return success;
}

} // namespace kotlin::mm
