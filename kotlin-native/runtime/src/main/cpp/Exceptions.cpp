/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#include <cstdlib>
#include <stdio.h>
#include <string.h>
#include <stdint.h>
#include <iomanip>
#include <iostream>
#include <sstream>

#include <exception>
#include <unistd.h>

#include "KAssert.h"
#include "Exceptions.h"
#include "ExecFormat.h"
#include "KString.h"
#include "Memory.h"
#include <std_support/Atomic.hpp>
#include "concurrent/Mutex.hpp"
#include "Porting.h"
#include "Types.h"
#include "Utils.hpp"
#include "ObjCExceptions.h"
#include "BuildIdUtils.h"

// region Tencent Code
#ifdef KONAN_OHOS

#include "Natives.h"
#include "KString.h"
#include "CompilerConstants.hpp"
#include <dlfcn.h>
#include <deviceinfo.h>
#include "hidebug/hidebug.h"
#include "hidebug/hidebug_type.h"

extern "C" OBJ_GETTER(Kotlin_Throwable_getStackTrace, KRef throwable);
extern "C" OBJ_GETTER(Kotlin_Throwable_getMessage, KRef throwable);
extern "C" __attribute__((weak)) void set_fatal_message(const char* msg);

constexpr int OHOS_HIDEBUG_MIN_API = 23;
constexpr unsigned long LARGE_BUFFER_SIZE = 64 * 1024;
constexpr unsigned long LARGE_BUFFER_RESERVED = 20;
constexpr unsigned long SMALL_BUFFER_SIZE = 1004;
constexpr int FRAME_NO_WIDTH = 2;
constexpr int PC_ADDR_WIDTH = 16;

unsigned long getFatalMessageSize()
{
    int apiVersion = OH_GetSdkApiVersion();
    if (apiVersion >= OHOS_HIDEBUG_MIN_API) {
        return LARGE_BUFFER_SIZE - LARGE_BUFFER_RESERVED;
    } else {
        return SMALL_BUFFER_SIZE;
    }
}

/**
 * @brief Build a human-readable exception summary: "ClassName: message".
 * @param exception The Kotlin exception object.
 * @return A string like "kotlin.IllegalStateException: something went wrong",
 *         or "unknown" if the information cannot be safely retrieved.
 */
static std::string getExceptionSummary(KRef exception)
{
    if (exception == nullptr || exception->type_info() == nullptr) {
        return "unknown";
    }

    std::string summary = exception->type_info()->fqName();

    ObjHolder messageHolder;
    KRef message = Kotlin_Throwable_getMessage(exception, messageHolder.slot());
    if (message != nullptr) {
        summary += ": ";
        summary += kotlin::to_string<KStringConversionMode::UNCHECKED>(message);
    }

    return summary;
}

// API >= OHOS_HIDEBUG_MIN_API (64K): standard readable backtrace format, e.g.:
//   #00 pc 00000000001a3f00 libA.so(buildid) (symbolName+0x10) (MyFile.kt:42)
//   #01 pc 0000000000002b4c libB.so(buildid)

static std::string buildStandardBacktrace(ArrayHeader* stackTrace, Dl_info& info,
                                          std::vector<MapsEntry>& mapCache,
                                          unsigned long messageSize,
                                          const std::string& reason)
{
    std::string result = "\nUncaught Kotlin exception at following addresses:\n";
    result += "Reason: " + reason + "\n";
    uint32_t frameNo = 0;

    for (uint32_t i = 0; i < stackTrace->count_; ++i) {
        KNativePtr ptr = *PrimitiveArrayAddressOfElementAt<KNativePtr>(stackTrace, i);
        if (dladdr(ptr, &info) == 0) {
            continue;
        }

        std::vector<uint8_t> buildId;
        std::string soPath = BuildIdUtils::findSoPathFromMaps(reinterpret_cast<uintptr_t>(ptr), mapCache);
        std::string soFileName = soPath.substr(soPath.find_last_of("/") + 1);
        std::string buildIdStr = BuildIdUtils::getSoBuildId(soPath, buildId);
        std::string soInfo = buildIdStr.empty() ? soFileName : soFileName + "(" + buildIdStr + ")";

        uintptr_t offset = reinterpret_cast<uintptr_t>(ptr) - reinterpret_cast<uintptr_t>(info.dli_fbase) - 1;
        std::stringstream ss;
        ss << "#" << std::setw(FRAME_NO_WIDTH) << std::setfill('0') << frameNo
           << " pc " << std::setw(PC_ADDR_WIDTH) << std::setfill('0') << std::hex << offset
           << " " << soInfo;

        if (info.dli_sname != nullptr) {
            uintptr_t symOffset = reinterpret_cast<uintptr_t>(ptr) - reinterpret_cast<uintptr_t>(info.dli_saddr);
            ss << " (" << info.dli_sname << "+0x" << std::hex << symOffset << ")";
        }

        ss << "\n";

        std::string line = ss.str();
        if (result.length() + line.length() >= messageSize) {
            break;
        }
        result += line;
        frameNo++;
    }

    return result;
}

// API < 23 (1004 bytes): compressed format to fit within the small buffer.
// Format: sofiles:\nlib1.so(id1),lib2.so(id2)\naddresses:\n[0] 0x1a 0x2b\n[1] 0x3c
static std::string buildCompressedBacktrace(ArrayHeader* stackTrace, Dl_info& info,
                                            std::vector<MapsEntry>& mapCache,
                                            unsigned long messageSize,
                                            const std::string& reason)
{
    std::string message = "\nUncaught Kotlin exception:\nReason: " + reason + "\n";
    std::vector<std::string> soFiles;
    std::string soFilesStr;
    std::string addressLine;
    std::string preIndex;
    // Fixed-format overhead: "sofiles:\n" (8) + "\n" (1) + "addresses:" (10) + small buffer (3) = 22
    constexpr size_t FATAL_MESSAGE_FORMAT_OVERHEAD = 22;
    size_t fixedOverhead = message.length() + FATAL_MESSAGE_FORMAT_OVERHEAD;

    for (uint32_t i = 0; i < stackTrace->count_; ++i) {
        KNativePtr ptr = *PrimitiveArrayAddressOfElementAt<KNativePtr>(stackTrace, i);
        if (dladdr(ptr, &info) == 0) {
            continue;
        }

        std::vector<uint8_t> buildId;
        std::string soPath = BuildIdUtils::findSoPathFromMaps(reinterpret_cast<uintptr_t>(ptr), mapCache);
        std::string soFileName = soPath.substr(soPath.find_last_of("/") + 1);
        std::string buildIdStr = BuildIdUtils::getSoBuildId(soPath, buildId);
        std::string soInfo = buildIdStr.empty() ? soFileName : soFileName + "(" + buildIdStr + ")";

        uintptr_t offset = reinterpret_cast<uintptr_t>(ptr) - reinterpret_cast<uintptr_t>(info.dli_fbase) - 1;
        std::stringstream ss;
        ss << std::hex << offset;
        std::string addr = "0x" + ss.str();

        int soIndex = -1;
        for (size_t j = 0; j < soFiles.size(); ++j) {
            if (soFiles[j] == soInfo) {
                soIndex = static_cast<int>(j);
                break;
            }
        }
        if (soIndex == -1) {
            soFiles.push_back(soInfo);
            soIndex = static_cast<int>(soFiles.size() - 1);
        }

        std::string indexStr = std::to_string(soIndex);
        std::string soFileStrTmp = (soFilesStr.find(soInfo) == std::string::npos) ? soInfo : "";
        std::string addressTmp = (preIndex.empty() || preIndex != indexStr)
            ? "\n[" + indexStr + "] " + addr
            : " " + addr;

        size_t totalLen = fixedOverhead + soFilesStr.length() + addressLine.length()
            + soFileStrTmp.length() + addressTmp.length();
        if (totalLen >= messageSize) {
            break;
        }

        soFilesStr += soFilesStr.empty() ? soFileStrTmp : (soFileStrTmp.empty() ? "" : "," + soFileStrTmp);
        addressLine += addressTmp;
        preIndex = indexStr;
    }

    std::string result = message + "sofiles:\n" + soFilesStr + "\n"
        + "addresses:" + (addressLine.empty() ? "" : addressLine);
    return result;
}

void ReportBacktraceToOhosLog(KRef exception)
{
    if (&set_fatal_message == nullptr && &OH_HiDebug_SetCrashObj == nullptr) {
        return;
    }

    ObjHolder stackTraceHolder;
    ArrayHeader* stackTrace = Kotlin_Throwable_getStackTrace(exception, stackTraceHolder.slot())->array();
    Dl_info info;

    pid_t pid = getpid();
    std::vector<MapsEntry> mapCache = BuildIdUtils::parseMapsFile(pid);

    int apiVersion = OH_GetSdkApiVersion();
    unsigned long messageSize = getFatalMessageSize();
    std::string reason = getExceptionSummary(exception);

    std::string fatalMessage = (apiVersion >= OHOS_HIDEBUG_MIN_API)
        ? buildStandardBacktrace(stackTrace, info, mapCache, messageSize, reason)
        : buildCompressedBacktrace(stackTrace, info, mapCache, messageSize, reason);

    static std::string truncated;
    truncated.assign(fatalMessage, 0, messageSize);
    if (apiVersion >= OHOS_HIDEBUG_MIN_API && &OH_HiDebug_SetCrashObj != nullptr) {
        OH_HiDebug_SetCrashObj(HiDebug_CrashObjType::HIDEBUG_CRASHOBJ_STRING,
                               (void*)truncated.c_str());
    } else if (&set_fatal_message != nullptr) {
        set_fatal_message(truncated.c_str());
    }
}

#endif
// endregion

// Defined in RuntimeUtils.kt
extern "C" void Kotlin_runUnhandledExceptionHook(KRef exception);
extern "C" void ReportUnhandledException(KRef exception);

void ThrowException(KRef exception) {
  RuntimeAssert(exception != nullptr && IsInstanceInternal(exception, theThrowableTypeInfo),
                "Throwing something non-throwable");
  ExceptionObjHolder::Throw(exception);
}

void ThrowIllegalStateExceptionFromCString(const char* message) {
  fflush(stderr);
  // Called from cinterop stub (Native state). Use CalledFromNativeGuard so Kotlin_initRuntimeIfNeeded()
  // runs and thread is registered before we switch to Runnable and call Kotlin code.
  kotlin::CalledFromNativeGuard guard(/* reentrant = */ true);
  ObjHolder holder;
  std::string fullMessage = message ? message : "";
  CreateStringFromCString(fullMessage.c_str(), holder.slot());
  ThrowIllegalStateExceptionWithMessage(holder.obj());
}

void HandleCurrentExceptionWhenLeavingKotlinCode() {
  try {
      std::rethrow_exception(std::current_exception());
  } catch (ExceptionObjHolder& e) {
      std::terminate();  // Terminate when it's a kotlin exception.
  }
}

namespace {

class {
    /**
     * Timeout 5 sec for concurrent (second) terminate attempt to give a chance the first one to finish.
     * If the terminate handler hangs for 5 sec it is probably fatally broken, so let's do abnormal _Exit in that case.
     */
    unsigned int timeoutSec = 5;
    std::atomic<int> terminatingFlag = 0;
  public:
    template <class Fun> RUNTIME_NORETURN void operator()(Fun block) {
      if (kotlin::std_support::atomic_compare_exchange_strong(terminatingFlag, 0, 1)) {
        block();
        // block() is supposed to be NORETURN, otherwise go to normal abort()
        std::abort();
      } else {
        kotlin::NativeOrUnregisteredThreadGuard guard(/* reentrant = */ true);
        sleep(timeoutSec);
        // We come here when another terminate handler hangs for 5 sec, that looks fatally broken. Go to forced exit now.
      }
      _Exit(EXIT_FAILURE); // force exit
    }
} concurrentTerminateWrapper;

void RUNTIME_NORETURN terminateWithUnhandledException(KRef exception) {
    kotlin::AssertThreadState(kotlin::ThreadState::kRunnable);
    concurrentTerminateWrapper([exception]() {
        ReportUnhandledException(exception);

        // Just in case.
        // At this stage we are more interested in the exception than in the thread state checker failure.
        kotlin::CallsCheckerIgnoreGuard ignoreCallChecks;

#if KONAN_REPORT_BACKTRACE_TO_IOS_CRASH_LOG
        ReportBacktraceToIosCrashLog(exception);
#endif
// region Tencent Code
#ifdef KONAN_OHOS
        ReportBacktraceToOhosLog(exception);
#endif
// endregion

        kotlin::ThreadStateGuard guard(kotlin::ThreadState::kNative);

        // Best effort to make sure the reported exception gets actually printed:
        konan::consoleFlush();

        std::abort();
    });
}

void processUnhandledException(KRef exception) noexcept {
    kotlin::AssertThreadState(kotlin::ThreadState::kRunnable);
    try {
        Kotlin_runUnhandledExceptionHook(exception);
    } catch (ExceptionObjHolder& e) {
        terminateWithUnhandledException(e.GetExceptionObject());
    }
}

} // namespace

PERFORMANCE_INLINE RUNTIME_NOTHROW OBJ_GETTER(Kotlin_getExceptionObject, void* holder) {
    RETURN_OBJ(static_cast<ExceptionObjHolder*>(holder)->GetExceptionObject());
}

namespace {
// Copy, move and assign would be safe, but not much useful, so let's delete all (rule of 5)
class TerminateHandler : private kotlin::Pinned {
  RUNTIME_NORETURN static void queuedHandler() {
      concurrentTerminateWrapper([]() {
          // Not a Kotlin exception - call default handler
          instance().queuedHandler_();
      });
  }

  // In fact, it's safe to call my_handler directly from outside: it will do the job and then invoke original handler,
  // even if it has not been initialized yet. So one may want to make it public and/or not the class member
  RUNTIME_NORETURN static void kotlinHandler() {
      if (auto currentException = std::current_exception()) {
        try {
          std::rethrow_exception(currentException);
        } catch (ExceptionObjHolder& e) {
          // Both thread states are allowed here because there is no guarantee that
          // C++ runtime will unwind the stack for an unhandled exception. Thus there
          // is no guarantee that state switches made on interop borders will be rolled back.

          // Moreover, a native code can catch an exception thrown by a Kotlin callback,
          // store it to a global and then re-throw it in another thread which is not attached
          // to the Kotlin runtime. To handle this case, use the CalledFromNativeGuard.
          // TODO: Forbid throwing Kotlin exceptions through the interop border to get rid of this case.
          kotlin::CalledFromNativeGuard guard(/* reentrant = */ true);
          processUnhandledException(e.GetExceptionObject());
          terminateWithUnhandledException(e.GetExceptionObject());
        } catch (...) {
          // Not a Kotlin exception - call default handler
          kotlin::NativeOrUnregisteredThreadGuard guard(/* reentrant = */ true);
          queuedHandler();
        }
      }
      // Come here in case of direct terminate() call or unknown exception - go to default terminate handler.
      kotlin::NativeOrUnregisteredThreadGuard guard(/* reentrant = */ true);
      queuedHandler();
  }

  using QH = __attribute__((noreturn)) void(*)();
  QH queuedHandler_;

  /// Use machinery like Meyers singleton to provide thread safety
  TerminateHandler()
    : queuedHandler_((QH)std::set_terminate(kotlinHandler)) {}

  static TerminateHandler& instance() {
    static TerminateHandler singleton [[clang::no_destroy]];
    return singleton;
  }

  // Dtor might be in use to restore original handler. However, consequent install
  // will not reconstruct handler anyway, so let's keep dtor deleted to avoid confusion.
  ~TerminateHandler() = delete;
public:
  /// First call will do the job, all consequent will do nothing.
  static void install() {
    instance(); // Use side effect of warming up
  }
};
} // anon namespace

// Use one public function to limit access to the class declaration
void SetKonanTerminateHandler() {
  TerminateHandler::install();
}

extern "C" void RUNTIME_NORETURN Kotlin_terminateWithUnhandledException(KRef exception) {
    kotlin::AssertThreadState(kotlin::ThreadState::kRunnable);
    terminateWithUnhandledException(exception);
}

extern "C" void Kotlin_processUnhandledException(KRef exception) {
    kotlin::AssertThreadState(kotlin::ThreadState::kRunnable);
    processUnhandledException(exception);
}

void kotlin::ProcessUnhandledException(KRef exception) noexcept {
    // This may be called from any state, do reentrant state switch to runnable.
    kotlin::ThreadStateGuard guard(kotlin::ThreadState::kRunnable, /* reentrant = */ true);
    processUnhandledException(exception);
}

void RUNTIME_NORETURN kotlin::TerminateWithUnhandledException(KRef exception) noexcept {
    // This may be called from any state, do reentrant state switch to runnable.
    kotlin::ThreadStateGuard guard(kotlin::ThreadState::kRunnable, /* reentrant = */ true);
    terminateWithUnhandledException(exception);
}
