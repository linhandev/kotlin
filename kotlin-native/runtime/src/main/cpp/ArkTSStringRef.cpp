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

#ifdef KONAN_OHOS

#include <deviceinfo.h>
#include <dlfcn.h>
#include "ArkTSConfig.h"
#include "ArkTSStringRef.h"
#include "Memory.h"
#include "MemoryManagerSwitch.hpp"
#include "Natives.h"
#include "Runtime.h"
#include "Types.h"

namespace {
// Minimum OpenHarmony SDK API level that exposes napi_open_critical_scope /
// napi_close_critical_scope / napi_get_buffer_string_utf16_in_critical_scope.
constexpr int kMinApiLevelForCriticalScope = 23;
// Minimum OpenHarmony SDK API level that exposes napi_create_external_string_utf16.
constexpr int kMinApiLevelForExternalString = 22;
} // namespace

// Forward declarations
typedef void (*napi_finalize_callback)(void* finalize_data, void* finalize_hint);
extern "C" void* CreateStablePointer(ObjHeader* object);
extern "C" void DisposeStablePointer(void* ref);
// DerefStablePointer is declared as OBJ_GETTER in Memory.cpp; spell out its
// post-macro-expansion signature here so ExternalStringFinalizer can recover
// the Kotlin object handle for CRT_UnPin without pulling in mm internals.
extern "C" ObjHeader* DerefStablePointer(void* ref, ObjHeader** OBJ_RESULT);

typedef napi_status (*OpenScopeFunc)(napi_env env, NapiCriticalScope* scope);
typedef napi_status (*CloseScopeFunc)(napi_env env, NapiCriticalScope scope);
typedef napi_status (*GetBufferStringFunc)(
    napi_env env, napi_value value, const char16_t** buffer, size_t* length);
typedef napi_status (*CreateExternalUtf16StringFunc)(
    napi_env env,
    const char16_t* str,
    size_t length,
    napi_finalize_callback finalize_callback,
    void* finalize_hint,
    napi_value* result);

struct {
    // napi_open_critical_scope.
    OpenScopeFunc openScope;
    // napi_close_critical_scope.
    CloseScopeFunc closeScope;
    // napi_get_buffer_string_utf16_in_critical_scope.
    GetBufferStringFunc getBufferString;
    // napi_create_external_string_utf16.
    CreateExternalUtf16StringFunc createExternalUtf16String;
} g_arkApis;

namespace {
// Indicates whether the napi_get_buffer_string_utf16_in_critical_scope API is available.
static bool g_isGetBufferStringApiAvailable = false;

// Indicates whether the napi_create_external_string_utf16 API is available.
static bool g_isCreateExternalStringApiAvailable = false;

// Main thread id.
const pid_t g_mainThreadId = getpid();

// Threadsafe function of main thread.
napi_threadsafe_function g_mainThreadSafeFunc = nullptr;

} // namespace

extern "C" {
void Kotlin_ArkTS_initStringRef() {
    int version = OH_GetSdkApiVersion();
    RuntimeLogInfo({ kotlin::logging::Tag::kRT },
                   "[StringZeroCopy] OH_GetSdkApiVersion() = %d", version);
    if (version >= kMinApiLevelForCriticalScope) {
        g_arkApis.openScope =
            (OpenScopeFunc)dlsym(RTLD_DEFAULT, "napi_open_critical_scope");
        g_arkApis.closeScope =
            (CloseScopeFunc)dlsym(RTLD_DEFAULT, "napi_close_critical_scope");
        g_arkApis.getBufferString = (GetBufferStringFunc)dlsym(
            RTLD_DEFAULT, "napi_get_buffer_string_utf16_in_critical_scope");
        RuntimeAssert(g_arkApis.openScope != nullptr,
                      "napi_open_critical_scope must be found");
        RuntimeAssert(g_arkApis.closeScope != nullptr,
                      "napi_close_critical_scope must be found");
        RuntimeAssert(g_arkApis.getBufferString != nullptr,
                      "napi_get_buffer_string_utf16_in_critical_scope must be found");
        g_isGetBufferStringApiAvailable =
            g_arkApis.openScope && g_arkApis.closeScope && g_arkApis.getBufferString;
    }
    if (version >= kMinApiLevelForExternalString) {
        g_arkApis.createExternalUtf16String = (CreateExternalUtf16StringFunc)dlsym(
            RTLD_DEFAULT, "napi_create_external_string_utf16");
        RuntimeAssert(g_arkApis.createExternalUtf16String != nullptr,
                      "napi_create_external_string_utf16 must be found");
        g_isCreateExternalStringApiAvailable =
            g_arkApis.createExternalUtf16String != nullptr;
    }
}

bool Kotlin_ArkTS_isGetBufferStringApiAvailable() {
    return g_isGetBufferStringApiAvailable;
}

bool Kotlin_ArkTS_isCreateExternalStringApiAvailable() {
    return g_isCreateExternalStringApiAvailable;
}
} // extern "C"

struct CallbackData {
    std::function<void()> callback;
};

namespace {
void CallJS(napi_env env, napi_value noUsed, void* context, void* data) {
    auto callbackData = reinterpret_cast<CallbackData*>(data);
    napi_handle_scope scope;
    napi_open_handle_scope(env, &scope);
    callbackData->callback();
    napi_close_handle_scope(env, scope);
    delete callbackData;
}

void RegisterThreadSafeFunctionIfNeeded(napi_env env) {
    if (g_mainThreadSafeFunc) {
        return;
    }
    napi_value name = nullptr;
    napi_status status = napi_create_string_utf8(env, "tsfn-worker", NAPI_AUTO_LENGTH, &name);
    if (status != napi_ok) {
        RuntimeLogError({ kotlin::logging::Tag::kRT },
                        "[String0Copy] RegisterThreadSafeFunctionIfNeeded: napi_create_string_utf8 failed, status = %d",
                        status);
        return;
    }
    status = napi_create_threadsafe_function(env, nullptr, nullptr, name, 0, 1,
                                             nullptr, nullptr, nullptr,
                                             CallJS, &g_mainThreadSafeFunc);
    if (status != napi_ok) {
        RuntimeLogError({ kotlin::logging::Tag::kRT },
                        "[String0Copy] RegisterThreadSafeFunctionIfNeeded: "
                        "napi_create_threadsafe_function failed, status = %d",
                        status);
        g_mainThreadSafeFunc = nullptr;
        return;
    }
    RuntimeLogInfo({ kotlin::logging::Tag::kRT },
                   "[String0Copy] RegisterThreadSafeFunctionIfNeeded: initialized for env = %p",
                   env);
}
} // namespace

void SubmitTaskToArkTSMainThread(std::function<void()> callback) {
    if (gettid() != g_mainThreadId) {
        auto callbackData = new CallbackData();
        callbackData->callback = callback;
        napi_call_threadsafe_function(g_mainThreadSafeFunc, callbackData, napi_tsfn_blocking);
    } else {
        callback();
    }
}

NapiScopeGuard::NapiScopeGuard(napi_env env) {
    RuntimeAssert(env, "NapiScopeGuard env must be valid");
    env_ = env;
    auto status = g_arkApis.openScope(env_, &scope_);
    RuntimeAssert(status == napi_ok, "napi_open_critical_scope failed(%d)", status);
}

ALWAYS_INLINE void NapiScopeGuard::Close() {
    if (scope_) {
        auto status = g_arkApis.closeScope(env_, scope_);
        RuntimeAssert(status == napi_ok, "napi_close_critical_scope failed(%d)", status);
        scope_ = nullptr;
    }
}

NapiScopeGuard::~NapiScopeGuard() {
    Close();
}

/**
 * NapiRefCleaner is a singleton class, it is used to batch deleting napi_refs to ensure
 * ArkTS can reclaim those strings.
 * NOTE:
 *   1. Only support napi_refs on the main thread.
 *   2. A clean task is automatically triggered when the number of napi_refs reaches
 *      a certain threshold(e.g. 500).
 *   3. If no clean task has been performed within the specified time(e.g. 10 seconds),
 *      and there are still pending napi_refs, a forced clean task will be triggered.
 */
class NapiRefCleaner {
public:
    NapiRefCleaner(const NapiRefCleaner&) = delete;
    NapiRefCleaner& operator=(const NapiRefCleaner&) = delete;

    static NapiRefCleaner& getInstance() {
        static NapiRefCleaner instance;
        return instance;
    }

    void push(napi_env env, napi_ref ref) {
        // Save napi_env of main thread at the first time.
        if (this->env_ == nullptr) {
            this->env_ = env;
        }

        std::vector<napi_ref> temp;
        {
            std::lock_guard<std::mutex> lock(mtx_);
            refList_.push_back(ref);
            // Trigger cleanup task when the number of napi_refs reaches the limit.
            if (refList_.size() >=
                    static_cast<size_t>(Kotlin_ArkTSConfig_getNapiRefCleanupBatchSize())) {
                refList_.swap(temp);
            }
        }

        if (!temp.empty()) {
            clean(temp);
        } else {
            startTimerIfNeeded();
        }
    }

    void clean(std::vector<napi_ref>& list) {
        // Post task to ArkTS main thread.
        SubmitTaskToArkTSMainThread([env = this->env_, list = std::move(list)]() {
            RuntimeLogInfo({ kotlin::logging::Tag::kRT },
                           "[String0Copy] NapiRefCleaner::clean: batch deleting napi_ref, count = %ld",
                           list.size());
            for (const auto ref : list) {
                auto status = napi_delete_reference(env, ref);
                if (status != napi_ok) {
                    RuntimeLogError({ kotlin::logging::Tag::kRT },
                                    "[String0Copy] NapiRefCleaner: napi_delete_reference failed, status = %d",
                                    status);
                }
            }
        });

        // Update last clean time.
        lastCleanTimeSec_.store(nowSeconds(), std::memory_order_relaxed);
    }

    ~NapiRefCleaner() {
        clean(refList_);
    }

private:
    static int64_t nowSeconds() {
        return std::chrono::duration_cast<std::chrono::seconds>(
            std::chrono::steady_clock::now().time_since_epoch()).count();
    }

    NapiRefCleaner() : lastCleanTimeSec_(nowSeconds()), timerRunning_(false) {}

    // One tick of the timer loop: snapshots the pending list if enough time has
    // elapsed since the last clean, and flushes it. Extracted to keep
    // startTimerIfNeeded / the timer loop shallow.
    void tryCleanDueLocked(int intervalSec) {
        auto elapsed = nowSeconds() - lastCleanTimeSec_.load(std::memory_order_relaxed);
        std::vector<napi_ref> temp;
        {
            std::lock_guard<std::mutex> lock(mtx_);
            if (elapsed >= intervalSec && !refList_.empty()) {
                refList_.swap(temp);
            }
        }
        if (!temp.empty()) {
            clean(temp);
        }
    }

    void runTimerLoop() {
        while (true) {
            auto intervalSec = Kotlin_ArkTSConfig_getNapiRefCleanupIntervalSec();
            std::this_thread::sleep_for(std::chrono::seconds(intervalSec));
            tryCleanDueLocked(intervalSec);
        }
    }

    void startTimerIfNeeded() {
        if (timerRunning_.exchange(true)) {
            return;
        }
        std::thread timerThread([this]() { runTimerLoop(); });
        timerThread.detach();
    }

    napi_env env_ {nullptr};
    std::vector<napi_ref> refList_;
    std::mutex mtx_;
    std::atomic<int64_t> lastCleanTimeSec_;
    std::atomic<bool> timerRunning_;
};

ArkTSStringRef* ArkTSStringRef::tryCreate(napi_env env, napi_value value) {
    // If api is not available, or current thread is not main thread, will fallback to copy.
    if (!Kotlin_ArkTSConfig_getArkToKotlinEnabled() ||
        !Kotlin_ArkTS_isGetBufferStringApiAvailable() ||
        gettid() != g_mainThreadId) {
        return nullptr;
    }
    const char16_t* buffer = nullptr;
    size_t length = 0;
    {
        NapiScopeGuard guard(env);
        auto status = g_arkApis.getBufferString(env, value, &buffer, &length);
        // This operation may fail due to the ArkTS string is not encoded in UTF-16.
        if (status != napi_ok) {
            RuntimeLogWarning(
                { kotlin::logging::Tag::kRT },
                "[String0Copy] ArkTSStringRef::tryCreate: napi_get_buffer_string_utf16_in_critical_scope failed(%d)",
                status);
            return nullptr;
        }
    }
    // Copy solution is better than string proxy for small strings.
    if (length < static_cast<size_t>(Kotlin_ArkTSConfig_getMinLengthForArkString())) {
        return nullptr;
    }
    kotlin::ThreadStateGuard guard(kotlin::ThreadState::kNative, true);
    // Initialize the main thread safe function if needed.
    RegisterThreadSafeFunctionIfNeeded(env);
    napi_ref ref = nullptr;
    auto status = napi_create_reference(env, value, 1, &ref);
    RuntimeAssert(status == napi_ok,
                  "ArkTSStringRef::tryCreate: napi_create_reference failed(%d)", status);
    RuntimeLogDebug({ kotlin::logging::Tag::kRT },
                    "[String0Copy] share ArkTS string to Kotlin, length = %zu", length);
    return new ArkTSStringRef(env, ref, length);
}

ArkTSStringRef::~ArkTSStringRef() {
    if (env_ == nullptr || ref_ == nullptr) {
        return;
    }
    NapiRefCleaner::getInstance().push(env_, ref_);
}

std::u16string_view ArkTSStringRef::getStringView() {
    if (hasCached_) {
        return cachedString_;
    }
    if (isSameThread()) {
        const char16_t* buffer = nullptr;
        size_t length = 0;
        RuntimeAssert(value_, "ArkTSStringRef::getStringView: napi_value must be valid");
        // Must ensure that the scope has been opened before invoking this function.
        auto status = g_arkApis.getBufferString(env_, value_, &buffer, &length);
        RuntimeAssert(status == napi_ok,
                      "ArkTSStringRef::getStringView: getBufferString failed(%d)", status);
        RuntimeAssert(buffer,
                      "ArkTSStringRef::getStringView: getBufferString must return valid buffer");
        return std::u16string_view(buffer, length);
    }

    // Accessing this object on another thread requires degradation to string copying.
    fallbackToCopy();
    return cachedString_;
}

void ArkTSStringRef::fallbackToCopy() {
    kotlin::ThreadStateGuard guard(kotlin::ThreadState::kNative, true);
    {
        std::unique_lock<std::mutex> lock(mutex_);
        if (hasCached_) {
            return;
        }
        if (isCaching_) {
            cv_.wait(lock, [this]() { return this->hasCached_.load(); });
            return;
        }
        isCaching_ = true;
    }

    SubmitTaskToArkTSMainThread([this]() {
        RuntimeLogWarning({ kotlin::logging::Tag::kRT },
                          "[String0Copy] ArkTSStringRef::getStringView: fallback to string copying");
        napi_value result = nullptr;
        auto status = napi_get_reference_value(this->env_, this->ref_, &result);
        RuntimeAssert(status == napi_ok,
                      "ArkTSStringRef: napi_get_reference_value failed, status = %d", status);
        size_t length = 0;
        status = napi_get_value_string_utf16(this->env_, result, nullptr, 0, &length);
        RuntimeAssert(status == napi_ok,
                      "ArkTSStringRef: napi_get_value_string_utf16 get length failed, status = %d",
                      status);
        std::u16string utf16(length, u'\0');
        status = napi_get_value_string_utf16(this->env_, result, utf16.data(), length + 1, nullptr);
        RuntimeAssert(status == napi_ok,
                      "ArkTSStringRef: napi_get_value_string_utf16 get data failed, status = %d",
                      status);
        {
            std::lock_guard<std::mutex> lock(mutex_);
            cachedString_ = std::move(utf16);
            hasCached_ = true;
            isCaching_ = false;
        }
        cv_.notify_all();
        // After copy, we can delete the napi_ref.
        napi_delete_reference(this->env_, this->ref_);
        this->ref_ = nullptr;
    });

    std::unique_lock<std::mutex> lock(mutex_);
    cv_.wait(lock, [this]() { return this->hasCached_.load(); });
}

napi_value ArkTSStringRef::toNapiValue(napi_env env) {
    kotlin::ThreadStateGuard guard(kotlin::ThreadState::kNative, true);
    std::unique_lock<std::mutex> lock(mutex_);
    if (isCaching_) {
        cv_.wait(lock, [this]() { return this->hasCached_.load(); });
    }
    if (hasCached_) {
        napi_value result = nullptr;
        auto status = napi_create_string_utf16(env, cachedString_.data(), length_, &result);
        RuntimeAssert(status == napi_ok, "napi_create_string_utf16 failed(%d)", status);
        return result;
    }
    return this->getNapiValue();
}

static void ExternalStringFinalizer(void *data, void* hint) {
    // Dispose stable pointer when ArkTS string is destroyed.
    if (hint != nullptr) {
        Kotlin_initRuntimeIfNeeded();
        ObjHeader* slot_ = nullptr;
        ObjHeader* obj = DerefStablePointer(hint, &slot_);
        if (obj != nullptr) {
            // CRT_UnPin reaches into CRT runtime state which is uninitialized under CMS GC;
            // wrap with checkUseCRT so the non-CRT path is a no-op.
            checkUseCRT<CheckMode::Slow>([&] {
                CRT_UnPin(reinterpret_cast<const void*>(obj));
            });
        }
        DisposeStablePointer(hint);
    }
}

/**
 * Cannot run with kNative thread state.
 */
napi_value CreateExternalStringUtf16(napi_env env, KConstRef thiz) {
    // Check if the configuration enabled, and if the API is available.
    if (!Kotlin_ArkTSConfig_getKotlinToArkEnabled()
            || !Kotlin_ArkTS_isCreateExternalStringApiAvailable()) {
        return nullptr;
    }

    auto strHeader = StringHeader::of(thiz);
    auto strLength = strHeader->size() / sizeof(char16_t);

    if (strLength < static_cast<size_t>(Kotlin_ArkTSConfig_getMinLengthForKotlinString())) {
        return nullptr;
    }

    kotlin::AssertThreadState(kotlin::ThreadState::kRunnable);
    // Create a stable pointer to prevent Kotlin GC from reclaiming the string during ArkTS usage.
    void *stablePtr = CreateStablePointer(const_cast<ObjHeader*>(thiz));
    napi_value result = nullptr;
    napi_status status = napi_ok;
    checkUseCRT<CheckMode::Slow>([&] {
        CRT_Pin(reinterpret_cast<const void*>(thiz));
    });
    {
        kotlin::ThreadStateGuard guard(kotlin::ThreadState::kNative, true);
        status = g_arkApis.createExternalUtf16String(
            env,
            reinterpret_cast<const char16_t*>(strHeader->data()),
            strLength,
            ExternalStringFinalizer,
            stablePtr,
            &result);
    }
    if (status != napi_ok) {
        checkUseCRT<CheckMode::Slow>([&] {
            CRT_UnPin(reinterpret_cast<const void*>(thiz));
        });
        DisposeStablePointer(stablePtr);
        RuntimeLogWarning({ kotlin::logging::Tag::kRT },
                          "[String0Copy] napi_create_external_string_utf16 failed, status = %d",
                          status);
        return nullptr;
    }

    RuntimeLogDebug({ kotlin::logging::Tag::kRT },
                    "[String0Copy] share Kotlin string to ArkTS, length = %zu", strLength);
    return result;
}

#endif // KONAN_OHOS
