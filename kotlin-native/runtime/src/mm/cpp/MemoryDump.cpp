/*
 * Copyright 2024 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "MemoryDump.hpp"

#include <unistd.h>
#include <algorithm>
#include <cstdio>
#include <cstring>
#include <limits>
#include <vector>
#include <queue>
#include <cinttypes>
#include <zlib.h>
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
#undef LOG_DOMAIN
#undef LOG_TAG
#define LOG_DOMAIN 0xFF00
#define LOG_TAG "DEBUG_MEMORYDUMP"
#endif

constexpr auto kTagMemDump = kotlin::logging::Tag::kMemoryDump;

namespace kotlin::mm {

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

// using OutputBuffer to buffer data before writing to gzFile
// , reducing the number of gzwrite calls and improving performance
// , especially for small writes;
class OutputBuffer {
public:
    static constexpr size_t kBufferSize = 1 * 1024 * 1024; // 1MB buffer

    explicit OutputBuffer(gzFile file) : file_(file), buffer_(new char[kBufferSize]), pos_(0) {}

    ~OutputBuffer() {
        Flush();
        delete[] buffer_;
    }

    void Write(const void* data, size_t size) {
        if (size > kBufferSize) {
            // Large block: write directly to file, bypass buffer
            Flush();
            const char* ptr = static_cast<const char*>(data);
            size_t remaining = size;
            const size_t kMaxChunk = static_cast<size_t>(std::numeric_limits<unsigned>::max());
            while (remaining > 0) {
                unsigned toWrite = static_cast<unsigned>(remaining > kMaxChunk ? kMaxChunk : remaining);
                gzwrite(file_, ptr, toWrite);
                ptr += toWrite;
                remaining -= toWrite;
            }
            return;
        }

        if (pos_ + size > kBufferSize) {
            Flush();
        }

        std::memcpy(buffer_ + pos_, data, size);
        pos_ += size;
    }

    void Flush() {
        if (pos_ > 0) {
            char* ptr = buffer_;
            size_t remaining = pos_;
            const size_t kMaxChunk = static_cast<size_t>(std::numeric_limits<unsigned>::max());
            while (remaining > 0) {
                unsigned toWrite = static_cast<unsigned>(remaining > kMaxChunk ? kMaxChunk : remaining);
                gzwrite(file_, ptr, toWrite);
                ptr += toWrite;
                remaining -= toWrite;
            }
            pos_ = 0;
        }
    }

private:
    gzFile file_;
    char* buffer_;
    size_t pos_;
};

class MemoryDumper {
public:
    static constexpr size_t kInitialObjectSetCapacity = 8388608; // 8M objects
    static constexpr size_t kInitialTypeSetCapacity = 4096;

    explicit MemoryDumper(gzFile file, bool isStrip) : file_(file), outputBuffer_(file), isStrip_(isStrip) {
        dumpedObjs_.Reserve(kInitialObjectSetCapacity);
        dumpedTypes_.Reserve(kInitialTypeSetCapacity);
        isStrip_ = isStrip;
    }

    // Dumps the memory and returns the success flag.
    void Dump() {
        RuntimeLogInfo({kTagMemDump}, "Starting to dump memory into %p", file_);

        DumpStr("Kotlin/Native dump 1.0.9");
        DumpBool(konan::isLittleEndian());
        DumpU8(sizeof(void*));

        RuntimeLogInfo({kTagMemDump}, "Dumping global roots");
        for (auto value : mm::GlobalRootSet()) {
            DumpTransitively(value);
        }

        RuntimeLogInfo({kTagMemDump}, "Dumping threads and thread roots");
        for (auto& thread : mm::GlobalData::Instance().threadRegistry().LockForIter()) {
            DumpThread(thread);
            for (auto value : mm::ThreadRootSet(thread)) {
                DumpTransitively(thread, value);
            }
        }

        RuntimeLogInfo({kTagMemDump}, "Dumping objects from the heap");
        GlobalData::Instance().allocator().TraverseAllocatedObjects([&](auto obj) { DumpTransitively(obj); });

        RuntimeLogInfo({kTagMemDump}, "Dumping extra objects from the heap");
        GlobalData::Instance().allocator().TraverseAllocatedExtraObjects([&](auto extraObj) { DumpTransitively(extraObj); });

        RuntimeLogInfo({kTagMemDump}, "Dumping stable references");
        DumpStableRefs();

        RuntimeLogInfo({kTagMemDump}, "Dumping enqueued objects");
        DumpEnqueuedObjects();

        RuntimeLogInfo({kTagMemDump}, "Dumping finished");
    }

private:
    template <typename T>
    void DumpSpan(std_support::span<T> span) {
        outputBuffer_.Write(span.data(), span.size() * sizeof(T));
    }

    template <typename T>
    void DumpValue(T value) {
        DumpSpan(std_support::span<T>(&value, 1));
    }

    void DumpId(const void* ptr) { DumpValue(ptr); }

    void DumpBool(bool b) { DumpU8(b ? 1 : 0); }

    void DumpU8(uint8_t i) { DumpValue(i); }

    void DumpU32(uint32_t i) { DumpValue(i); }

    void DumpStr(const char* str) { DumpSpan(std_support::span<const char>(str, strlen(str) + 1)); }

    void DumpString(ObjHeader* obj) {
        char* str = CreateCStringFromString(obj);
        DumpStr(str);
        DisposeCString(str);
    }

    void DumpStringOrEmptyIfNull(ObjHeader* obj) {
        if (obj) {
            DumpString(obj);
        } else {
            DumpStr("");
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

    void DumpObject(const TypeInfo* type, ObjHeader* obj) {
        RuntimeLogDebug({kTagMemDump}, "Dumping object %p of type %s", obj, type->fqName().c_str());
        DumpU8(TAG_OBJECT);
        DumpId(obj);
        DumpId(type);

        size_t size = type->instanceSize_;
        size_t dataOffset = sizeof(TypeInfo*);
        size_t dataSize = size - dataOffset;
        uint8_t* data = reinterpret_cast<uint8_t*>(obj) + dataOffset;

        DumpU32(dataSize);
        DumpSpan(std_support::span<uint8_t>(data, dataSize));
    }

    void DumpArray(const TypeInfo* type, ArrayHeader* arr) {
        RuntimeLogDebug({kTagMemDump}, "Dumping array %p", arr);
        DumpU8(TAG_ARRAY);
        DumpId(arr);
        DumpId(type);

        uint32_t count = arr->count_;
        DumpU32(count);

        int32_t elementSize = -type->instanceSize_;
        size_t dataOffset = alignUp(sizeof(ArrayHeader), elementSize);

        if ( isStrip_ && (type == theByteArrayTypeInfo || type == theCharArrayTypeInfo || type == theStringTypeInfo) ) {
            // ByteArray and CharArray (String backing): strip content to save space.
            // kdumputil reconstructs zero-filled data from count * elementSize.
            DumpU32(0);
        } else {
            // Primitive array: write raw data.
            size_t dataSize = elementSize * count;
            DumpU32(dataSize);

            uint8_t* data = reinterpret_cast<uint8_t*>(arr) + dataOffset;
            DumpSpan(std_support::span<uint8_t>(data, dataSize));
        }
    }

    void DumpObjectOrArray(ObjHeader* obj) {
        const TypeInfo* type = obj->type_info();
        if (type->IsArray()) {
            DumpArray(type, obj->array());
        } else {
            DumpObject(type, obj);
        }
    }

    void DumpType(const TypeInfo* type) {
        RuntimeLogDebug({kTagMemDump}, "Dumping type %s", type->fqName().c_str());
        DumpU8(TAG_TYPE);
        DumpId(type);

        bool isArray = type->IsArray();
        bool isExtended = type->extendedInfo_ != nullptr;
        bool isObjectArray = type == theArrayTypeInfo;
        uint8_t flags =
                (isArray ? TYPE_FLAG_ARRAY : 0) | (isExtended ? TYPE_FLAG_EXTENDED : 0) | (isObjectArray ? TYPE_FLAG_OBJECT_ARRAY : 0);
        DumpU8(flags);

        DumpId(type->superType_);

        DumpStringOrEmptyIfNull(type->packageName_);
        DumpStringOrEmptyIfNull(type->relativeName_);

        if (type->IsArray()) {
            DumpArrayInfo(type);
        } else {
            DumpObjectInfo(type);
        }
    }

    void DumpArrayInfo(const TypeInfo* type) {
        int32_t elementSize = -type->instanceSize_;
        DumpU32(elementSize);

        if (type->extendedInfo_ != nullptr) {
            DumpArrayInfo(type->extendedInfo_);
        }
    }

    void DumpArrayInfo(const ExtendedTypeInfo* extendedInfo) {
        uint8_t elementType = -extendedInfo->fieldsCount_;
        DumpU8(elementType);
    }

    void DumpObjectInfo(const TypeInfo* type) {
        size_t dataOffset = sizeof(TypeInfo*);

        DumpU32(type->instanceSize_ - dataOffset);
        DumpOffsets(type, dataOffset);

        if (type->extendedInfo_ != nullptr) {
            DumpObjectInfo(type->extendedInfo_, dataOffset);
        }
    }

    void DumpOffsets(const TypeInfo* type, size_t dataOffset) {
        int32_t count = type->objOffsetsCount_;
        DumpU32(count);
        for (int32_t i = 0; i < count; i++) {
            DumpU32(type->objOffsets_[i] - dataOffset);
        }
    }

    void DumpObjectInfo(const ExtendedTypeInfo* extendedInfo, size_t dataOffset) {
        int32_t fieldsCount = extendedInfo->fieldsCount_;
        DumpU32(fieldsCount);
        for (int32_t i = 0; i < fieldsCount; i++) {
            DumpU32(extendedInfo->fieldOffsets_[i] - dataOffset);
            DumpU8(extendedInfo->fieldTypes_[i]);
            DumpStr(extendedInfo->fieldNames_[i]);
        }
    }

    void DumpTransitively(const TypeInfo* type) {
        if (dumpedTypes_.Insert(type)) {
            // Dump super-type recursively, as the depth is not going to be a problem.
            if (type->superType_ != nullptr) {
                DumpTransitively(type->superType_);
            }

            DumpType(type);
        }
    }

    void DumpTransitively(ObjHeader* obj) {
        if (dumpedObjs_.Insert(obj)) {
            DumpTransitively(obj->type_info());

            DumpObjectOrArray(obj);

            // Enqueue referred objects to dump, as dumping them recursively may cause
            // stack overflow.
            traverseReferredObjects(obj, [&](auto refObj) { Enqueue(refObj); });
        }
    }

    void DumpTransitively(ExtraObjectData* extraObj) {
        RuntimeLogDebug({kTagMemDump}, "Dumping extra object %p", extraObj);
        DumpU8(TAG_EXTRA_OBJECT);
        DumpId(extraObj);

        ObjHeader* baseObj = extraObj->GetBaseObject();
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

    // Target file.
    gzFile file_;
    OutputBuffer outputBuffer_;

    // A set of already dumped type pointers.
    PointerSet dumpedTypes_;

    // A set of already dumped objects.
    PointerSet dumpedObjs_;

    // A queue of objects to dump transitively.
    std::queue<ObjHeader*> objQueue_;

    bool isStrip_ = false;
};

void PrepareForMemoryDump() {
    mm::GlobalData::Instance().threadRegistry().PublishAll();
}

void DumpMemoryOrThrow(int fd, bool isStrip) {
    gzFile file = gzdopen(fd, "w");
    if (!file) {
        throw std::system_error(errno, std::generic_category());
    }

    // Perform memory dump with zlib compression
    MemoryDumper(file, isStrip).Dump();

    // gzclose flushes remaining data, finalizes the gzip stream,
    // and closes the underlying file descriptor.
    int ret = gzclose(file);
    if (ret != Z_OK) {
        const char* zmsg = zError(ret);
        throw std::runtime_error(std::string("zlib error: ") + (zmsg ? zmsg : "unknown"));
    }
}

bool DumpMemory(int fd, bool isStrip) noexcept {
    PrepareForMemoryDump();

    bool success = true;
    try {
#ifndef KONAN_OHOS
        DumpMemoryOrThrow(fd, isStrip);
#else
        OH_LOG_INFO(LOG_APP, "Attempting to fork process for memory dump.");
        int pid = fork();
        OH_LOG_INFO(LOG_APP, "Forked process %d for memory dump.", pid);
        if (pid < 0) {
            OH_LOG_ERROR(LOG_APP, "Failed to fork process for memory dump.");
            return false;
        }
        if (pid == 0) {
            DumpMemoryOrThrow(fd, isStrip);
            OH_LOG_INFO(LOG_APP, "Forked process memory dump done.");
            exit(0);
        }
#endif
    } catch (const std::system_error& e) {
        success = false;
        RuntimeLogError({kTagMemDump}, "Memory dump error: %s", e.what());
    }
    return success;
}

} // namespace kotlin::mm
