/*
 * Copyright (c) 2026 Huawei Device Co., Ltd.
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

#ifndef RUNTIME_ARKTSSTRINGREF_H
#define RUNTIME_ARKTSSTRINGREF_H

#include "Common.h"
#include "KAssert.h"
#include "Logging.hpp"

#ifdef KONAN_OHOS
#include <string>
#include <thread>
#include <mutex>
#include <unistd.h>
#include <vector>
#include <napi/native_api.h>

#include "Types.h"
#include "KStringExtract.h"
#include "Runtime.h"

typedef void* NapiCriticalScope;

// Obtain the system's API level to determine whether the relevant APIs for
// ArkTS string zero-copy sharing are supported.
extern "C" void Kotlin_ArkTS_initStringRef();
extern "C" bool Kotlin_ArkTS_isGetBufferStringApiAvailable();
extern "C" bool Kotlin_ArkTS_isCreateExternalStringApiAvailable();

// Encapsulate the operation of opening the scope and closing the scope using the RAII mechanism.
class NapiScopeGuard {
public:
    // Disable copy construction and assignment.
    NapiScopeGuard(const NapiScopeGuard&) = delete;
    NapiScopeGuard& operator=(const NapiScopeGuard&) = delete;

    explicit NapiScopeGuard(napi_env env);
    ~NapiScopeGuard();
    ALWAYS_INLINE void Close();

private:
    napi_env env_ {nullptr};
    NapiCriticalScope scope_ {nullptr};
    // Ensures native thread state for NAPI calls in ctor/dtor. Declared after scope_ so it's destroyed first,
    // maintaining native state when scope_'s dtor makes NAPI calls (RAII destruction order is reverse of declaration).
    kotlin::ThreadStateGuard threadStateGuard_ {kotlin::ThreadState::kNative, true};
};

// Hold the napi_ref of ArkTS string.
class ArkTSStringRef {
public:
    static ArkTSStringRef* tryCreate(napi_env env, napi_value value);

    // Test-only factory. Constructs an ArkTSStringRef whose contents are
    // pre-cached from `content`, so every access path bypasses NAPI entirely
    // (env_/ref_ remain null, the destructor is a no-op for the NapiRefCleaner
    // path). Use only from unit tests.
    static ArkTSStringRef* createForTest(std::u16string content) {
        auto* self = new ArkTSStringRef(nullptr, nullptr, content.size());
        self->cachedString_ = std::move(content);
        self->hasCached_.store(true, std::memory_order_release);
        return self;
    }

    // Access this object safely as a string_view; ensure there is no open scope before invocation.
    // NOTE: Within the callback, do NOT call other NAPI methods, allocate new ArkTS objects,
    // perform blocking operations, or invoke other methods of this class (such as copyTo, getChar,
    // etc.). Otherwise, undefined behavior may occur.
    template<typename Func>
    ALWAYS_INLINE auto withStringView(Func&& callback) {
        if (this->needOpenScope()) {
            NapiScopeGuard guard(this->env_);
            return callback(this->getStringView());
        } else {
            return callback(this->getStringView());
        }
    }

    // Access this object and `other` object safely as a string_view; ensure there is no open scope
    // before invocation. NOTE: Within the callback, do NOT call other NAPI methods, allocate new
    // ArkTS objects, perform blocking operations, or invoke other methods of this class (such as
    // copyTo, getChar, etc.). Otherwise, undefined behavior may occur.
    template<typename Func>
    ALWAYS_INLINE auto withStringView(ArkTSStringRef* other, Func&& callback) {
        auto thizNeedOpen = this->needOpenScope();
        auto otherNeedOpen = other->needOpenScope();
        if (thizNeedOpen && otherNeedOpen) {
            NapiScopeGuard guard(this->env_);
            std::u16string_view thizView = this->getStringView();
            std::u16string_view otherView = other->getStringView();
            return callback(thizView, otherView);
        } else if (thizNeedOpen) {
            std::u16string_view otherView = other->getStringView();
            NapiScopeGuard guard(this->env_);
            std::u16string_view thizView = this->getStringView();
            return callback(thizView, otherView);
        } else if (otherNeedOpen) {
            std::u16string_view thizView = this->getStringView();
            NapiScopeGuard guard(other->env_);
            std::u16string_view otherView = other->getStringView();
            return callback(thizView, otherView);
        } else {
            std::u16string_view thizView = this->getStringView();
            std::u16string_view otherView = other->getStringView();
            return callback(thizView, otherView);
        }
    }

    // Convert proxy to napi_value, handling cache and concurrency.
    napi_value toNapiValue(napi_env env);

    // Get string length.
    ALWAYS_INLINE size_t getLength() const { return length_; }

    std::optional<KInt> getHashCode() {
        if (length_ == 0) return 0;
        if (!hasHashCode_.load(std::memory_order_acquire)) {
            return std::nullopt;
        }
        return cachedHashCode_.load(std::memory_order_relaxed);
    }

    void setHashCode(KInt hashCode) {
        cachedHashCode_.store(hashCode, std::memory_order_relaxed);
        hasHashCode_.store(true, std::memory_order_release);
    }

    // Copy string to dest.
    ALWAYS_INLINE void copyTo(void* dest, size_t length, int offset = 0) {
        withStringView([=](std::u16string_view view) {
            memcpy(dest, view.data() + offset, length);
        });
    }

    // Get char by index.
    ALWAYS_INLINE char16_t getChar(size_t index) {
        // Bounds check must happen outside the NAPI critical scope: throwing a Kotlin exception
        // from inside withStringView's callback would leak the scope (see withStringView NOTE).
        if (static_cast<uint32_t>(index) >= length_) {
            ThrowArrayIndexOutOfBoundsException();
        }
        return withStringView([&](std::u16string_view view) {
            return view[index];
        });
    }

    ALWAYS_INLINE const char16_t* getStringAddressOf(int32_t index) {
        fallbackToCopy();
        return this->withStringView([index](std::u16string_view thizView) {
            return thizView.data() + index;
        });
    }

    template <KStringConversionMode mode>
    std::string to_string(size_t start = 0, size_t size = std::string::npos)
            noexcept(mode != KStringConversionMode::CHECKED) {
        return withStringView([=](std::u16string_view sv) {
            RuntimeAssert(start <= length_, "start index out of bounds");
            auto it = sv.begin() + start;
            auto end = sv.end();
            if (size != std::string::npos) {
                RuntimeAssert(size <= length_ - start, "size out of bounds");
                end = it + size;
            }
            return hmm::string::to_string_impl<mode>(
                reinterpret_cast<const KChar*>(&*it),
                reinterpret_cast<const KChar*>(&*end));
        });
    }

    ~ArkTSStringRef();

private:
    // Get napi_value from napi_ref, returns nullptr if ref_ has been released.
    ALWAYS_INLINE napi_value getNapiValue() const {
        napi_ref ref = this->ref_.load(std::memory_order_acquire);
        if (ref == nullptr) return nullptr;
        kotlin::ThreadStateGuard guard(kotlin::ThreadState::kNative, true);
        napi_value result = nullptr;
        auto status = napi_get_reference_value(this->env_, ref, &result);
        RuntimeAssert(status == napi_ok,
                      "napi_get_reference_value failed, status = %d, ref_=%p",
                      status, ref);
        return result;
    }

    ArkTSStringRef(napi_env env, napi_ref ref, size_t length)
        : env_(env), ref_(ref), length_(length) {
        // Record current thread id when creating this object.
        threadId_ = gettid();
    }

    ALWAYS_INLINE bool isSameThread() {
        return threadId_ == gettid();
    }

    ALWAYS_INLINE bool needOpenScope() {
        if (hasCached_.load(std::memory_order_acquire) || !isSameThread()) {
            return false;
        }
        // Get napi_value before opening scope.
        value_ = getNapiValue();
        return true;
    }

    // Access the ArkTS string in the form of a u16string_view.
    std::u16string_view getStringView();

    void fallbackToCopy();

    napi_env env_ {nullptr};
    std::atomic<napi_ref> ref_ {nullptr};
    napi_value value_ {nullptr};
    pid_t threadId_ {0};
    size_t length_ {0};
    std::u16string cachedString_;
    std::atomic<bool> hasCached_ {false};
    std::atomic<bool> hasHashCode_ {false};
    std::atomic<KInt> cachedHashCode_ {0};
    bool isCaching_ {false};
    std::mutex mutex_;
    std::condition_variable cv_;
};

napi_value CreateExternalStringUtf16(napi_env env, KConstRef thiz);

#endif // KONAN_OHOS

#endif // RUNTIME_ARKTSSTRINGREF_H
