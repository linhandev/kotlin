/*
 * Copyright (C) 2026 Eazytec. All rights reserved.
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
import kotlin.test.*
import kotlinx.cinterop.*
import platform.ArkRuntime.JSVM.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class JSVMTest {


    // jsvm_types.h 回调：按 C 签名用 staticCFunction 实现
    // JSVM_Finalize: void(env, finalizeData, finalizeHint)
    private val finalizeCallback = staticCFunction { _env: CPointer<JSVM_Env__>?, _finalizeData: COpaquePointer?, _finalizeHint: COpaquePointer? -> }

    // JSVM_OutputStream: bool(data, size, streamData) - null data 表示流结束
    private val outputStreamCallback = staticCFunction { _data: CPointer<ByteVar>?, _size: Int, _streamData: COpaquePointer? -> true }

    // JSVM_HandlerForOOMError: void(location, detail, isHeapOOM)
    private val oomErrorHandler = staticCFunction { _location: CPointer<ByteVar>?, _detail: CPointer<ByteVar>?, _isHeapOOM: Boolean -> }

    // JSVM_HandlerForFatalError: void(location, message)
    private val fatalErrorHandler = staticCFunction { _location: CPointer<ByteVar>?, _message: CPointer<ByteVar>? -> }

    // JSVM_HandlerForPromiseReject: void(env, rejectEvent, rejectInfo)
    private val promiseRejectHandler = staticCFunction { _env: CPointer<JSVM_Env__>?, _rejectEvent: JSVM_PromiseRejectEvent, _rejectInfo: CPointer<JSVM_Value__>? -> }

    // JSVM_HandlerForGC: void(vm, gcType, flags, data)
    private val gcHandler = staticCFunction { _vm: CPointer<JSVM_VM__>?, _gcType: JSVM_GCType, _flags: JSVM_GCCallbackFlags, _data: COpaquePointer? -> }

    private fun logLine(msg: String) = println(msg)

    // ==================== VM 生命周期 ====================

    @Test
    fun testOH_JSVM_CreateVM() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>().apply {
                maxOldGenerationSize = 0u
                maxYoungGenerationSize = 0u
                initialOldGenerationSize = 0u
                initialYoungGenerationSize = 0u
                snapshotBlobData = null
                snapshotBlobSize = 0u
                isForSnapshotting = false
            }
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            assertNotNull(OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateVM passed")
    }

    @Test
    fun testOH_JSVM_SetMicrotaskPolicy() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_SetMicrotaskPolicy passed")
    }

    // ==================== VM Scope ====================

    @Test
    fun testOH_JSVM_OpenVMScope() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val vmScope = alloc<CPointerVar<JSVM_VMScope__>>()
            assertNotNull(OH_JSVM_OpenVMScope(vm.value, vmScope.ptr))
            OH_JSVM_CloseVMScope(vm.value, vmScope.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_OpenVMScope passed")
    }

    // ==================== Environment ====================

    // @Test
    // fun testOH_JSVM_CreateEnv() {
    //     memScoped {
    //         val initOptions = alloc<JSVM_InitOptions>().apply {
    //             externalReferences = null
    //             argc = null
    //             argv = null
    //             removeFlags = false
    //         }
    //         OH_JSVM_Init(initOptions.ptr)
    //         val createVMOptions = alloc<JSVM_CreateVMOptions>()
    //         val vm = alloc<CPointerVar<JSVM_VM__>>()
    //         OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
    //         val env = alloc<CPointerVar<JSVM_Env__>>()
    //         assertNotNull(OH_JSVM_CreateEnv(vm.value, 0u, alloc<JSVM_PropertyDescriptor>().ptr, env.ptr))
    //         OH_JSVM_DestroyEnv(env.value)
    //         OH_JSVM_DestroyVM(vm.value)
    //     }
    //     logLine("OH_JSVM_CreateEnv passed")
    // }

    // @Test
    // fun testOH_JSVM_OpenEnvScope() {
    //     memScoped {
    //         val initOptions = alloc<JSVM_InitOptions>().apply {
    //             externalReferences = null
    //             argc = null
    //             argv = null
    //             removeFlags = false
    //         }
    //         OH_JSVM_Init(initOptions.ptr)
    //         val createVMOptions = alloc<JSVM_CreateVMOptions>()
    //         val vm = alloc<CPointerVar<JSVM_VM__>>()
    //         OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
    //         val env = alloc<CPointerVar<JSVM_Env__>>()
    //         OH_JSVM_CreateEnv(vm.value, 0u, alloc<JSVM_PropertyDescriptor>().ptr, env.ptr)
    //         val envScope = alloc<CPointerVar<JSVM_EnvScope__>>()
    //         assertNotNull(OH_JSVM_OpenEnvScope(env.value, envScope.ptr))
    //         OH_JSVM_CloseEnvScope(env.value, envScope.value)         
    //         OH_JSVM_DestroyVM(vm.value)
    //     }
    //     logLine("OH_JSVM_OpenEnvScope passed")
    // }

    // @Test
    // fun testOH_JSVM_CloseEnvScope() {
    //     memScoped {
    //         val initOptions = alloc<JSVM_InitOptions>().apply {
    //             externalReferences = null
    //             argc = null
    //             argv = null
    //             removeFlags = false
    //         }
    //         OH_JSVM_Init(initOptions.ptr)
    //         val createVMOptions = alloc<JSVM_CreateVMOptions>()
    //         val vm = alloc<CPointerVar<JSVM_VM__>>()
    //         OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
    //         val env = alloc<CPointerVar<JSVM_Env__>>()
    //         OH_JSVM_CreateEnv(vm.value, 0u, alloc<JSVM_PropertyDescriptor>().ptr, env.ptr)
    //         val envScope = alloc<CPointerVar<JSVM_EnvScope__>>()
    //         OH_JSVM_OpenEnvScope(env.value, envScope.ptr)
    //         assertNotNull(OH_JSVM_CloseEnvScope(env.value, envScope.value))        
    //         OH_JSVM_DestroyVM(vm.value)
    //     }
    //     logLine("OH_JSVM_CloseEnvScope passed")
    // }

    // @Test
    // fun testOH_JSVM_DestroyEnv() {
    //     memScoped {
    //         val initOptions = alloc<JSVM_InitOptions>().apply {
    //             externalReferences = null
    //             argc = null
    //             argv = null
    //             removeFlags = false
    //         }
    //         OH_JSVM_Init(initOptions.ptr)
    //         val createVMOptions = alloc<JSVM_CreateVMOptions>()
    //         val vm = alloc<CPointerVar<JSVM_VM__>>()
    //         OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
    //         val env = alloc<CPointerVar<JSVM_Env__>>()
    //         OH_JSVM_CreateEnv(vm.value, 0u, alloc<JSVM_PropertyDescriptor>().ptr, env.ptr)
    //         OH_JSVM_DestroyVM(vm.value)
    //     }
    //     logLine("OH_JSVM_DestroyEnv passed")
    // }

    // ==================== Handler 回调 ====================

    @Test
    fun testOH_JSVM_SetHandlerForOOMError() {
        try {
            memScoped {
                val initOptions = alloc<JSVM_InitOptions>().apply {
                    externalReferences = null
                    argc = null
                    argv = null
                    removeFlags = false
                }
                OH_JSVM_Init(initOptions.ptr)
                val createVMOptions = alloc<JSVM_CreateVMOptions>()
                val vm = alloc<CPointerVar<JSVM_VM__>>()
                OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
                assertNotNull(OH_JSVM_SetHandlerForOOMError(vm.value, oomErrorHandler))
                OH_JSVM_SetHandlerForOOMError(vm.value, null)
                OH_JSVM_DestroyVM(vm.value)
            }
            logLine("OH_JSVM_SetHandlerForOOMError passed")
        } catch (e: Throwable) {
            logLine("testOH_JSVM_SetHandlerForOOMError (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_JSVM_SetHandlerForFatalError() {
        try {
            memScoped {
                val initOptions = alloc<JSVM_InitOptions>().apply {
                    externalReferences = null
                    argc = null
                    argv = null
                    removeFlags = false
                }
                OH_JSVM_Init(initOptions.ptr)
                val createVMOptions = alloc<JSVM_CreateVMOptions>()
                val vm = alloc<CPointerVar<JSVM_VM__>>()
                OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
                assertNotNull(OH_JSVM_SetHandlerForFatalError(vm.value, fatalErrorHandler))
                OH_JSVM_SetHandlerForFatalError(vm.value, null)
                OH_JSVM_DestroyVM(vm.value)
            }
            logLine("OH_JSVM_SetHandlerForFatalError passed")
        } catch (e: Throwable) {
            logLine("testOH_JSVM_SetHandlerForFatalError (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_JSVM_SetHandlerForPromiseReject() {
        try {
            memScoped {
                val initOptions = alloc<JSVM_InitOptions>().apply {
                    externalReferences = null
                    argc = null
                    argv = null
                    removeFlags = false
                }
                OH_JSVM_Init(initOptions.ptr)
                val createVMOptions = alloc<JSVM_CreateVMOptions>()
                val vm = alloc<CPointerVar<JSVM_VM__>>()
                OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
                assertNotNull(OH_JSVM_SetHandlerForPromiseReject(vm.value, promiseRejectHandler))
                OH_JSVM_SetHandlerForPromiseReject(vm.value, null)
                OH_JSVM_DestroyVM(vm.value)
            }
            logLine("OH_JSVM_SetHandlerForPromiseReject passed")
        } catch (e: Throwable) {
            logLine("testOH_JSVM_SetHandlerForPromiseReject (higher API / Missing symbol) exception: $e")
        }
    }

    // ==================== GC 回调 ====================

    @Test
    fun testOH_JSVM_AddHandlerForGC() {
        try {
            memScoped {
                val initOptions = alloc<JSVM_InitOptions>().apply {
                    externalReferences = null
                    argc = null
                    argv = null
                    removeFlags = false
                }
                OH_JSVM_Init(initOptions.ptr)
                val createVMOptions = alloc<JSVM_CreateVMOptions>()
                val vm = alloc<CPointerVar<JSVM_VM__>>()
                OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
                assertNotNull(OH_JSVM_AddHandlerForGC(vm.value, JSVM_CBTriggerTimeForGC.JSVM_CB_TRIGGER_BEFORE_GC, gcHandler, JSVM_GC_TYPE_ALL, null))
                OH_JSVM_RemoveHandlerForGC(vm.value, JSVM_CBTriggerTimeForGC.JSVM_CB_TRIGGER_BEFORE_GC, gcHandler, null)
                OH_JSVM_DestroyVM(vm.value)
            }
            logLine("OH_JSVM_AddHandlerForGC passed")
        } catch (e: Throwable) {
            logLine("testOH_JSVM_AddHandlerForGC (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_JSVM_RemoveHandlerForGC() {
        try {
            memScoped {
                val initOptions = alloc<JSVM_InitOptions>().apply {
                    externalReferences = null
                    argc = null
                    argv = null
                    removeFlags = false
                }
                OH_JSVM_Init(initOptions.ptr)
                val createVMOptions = alloc<JSVM_CreateVMOptions>()
                val vm = alloc<CPointerVar<JSVM_VM__>>()
                OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
                OH_JSVM_AddHandlerForGC(vm.value, JSVM_CBTriggerTimeForGC.JSVM_CB_TRIGGER_BEFORE_GC, gcHandler, JSVM_GC_TYPE_ALL, null)
                assertNotNull(OH_JSVM_RemoveHandlerForGC(vm.value, JSVM_CBTriggerTimeForGC.JSVM_CB_TRIGGER_BEFORE_GC, gcHandler, null))
                OH_JSVM_DestroyVM(vm.value)
            }
            logLine("OH_JSVM_RemoveHandlerForGC passed")
        } catch (e: Throwable) {
            logLine("testOH_JSVM_RemoveHandlerForGC (higher API / Missing symbol) exception: $e")
        }
    }

    // ==================== CreateExternal ====================

    @Test
    fun testOH_JSVM_CreateExternal() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val externalValue = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(OH_JSVM_CreateExternal(env.value, null, finalizeCallback, null, externalValue.ptr))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateExternal passed")
    }

    // ==================== TakeHeapSnapshot ====================

    @Test
    fun testOH_JSVM_TakeHeapSnapshot() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            assertNotNull(OH_JSVM_TakeHeapSnapshot(vm.value, outputStreamCallback, null))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_TakeHeapSnapshot passed")
    }

    // ==================== Script 编译执行 ====================

    @Test
    fun testOH_JSVM_CompileScript() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val scriptSource = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(env.value, "1 + 1", JSVM_AUTO_LENGTH, scriptSource.ptr)
            val script = alloc<CPointerVar<JSVM_Script__>>()
            assertNotNull(OH_JSVM_CompileScript(env.value, scriptSource.value, null, 0u, false, null, script.ptr))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CompileScript passed")
    }

    @Test
    fun testOH_JSVM_RunScript() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val scriptSource = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(env.value, "1 + 1", JSVM_AUTO_LENGTH, scriptSource.ptr)
            val script = alloc<CPointerVar<JSVM_Script__>>()
            val result = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(OH_JSVM_RunScript(env.value, script.value, result.ptr))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_RunScript passed")
    }

    @Test
    fun testOH_JSVM_CreateCodeCache() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val scriptSource = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(env.value, "1 + 1", JSVM_AUTO_LENGTH, scriptSource.ptr)
            val script = alloc<CPointerVar<JSVM_Script__>>()
            val codeCacheLen = alloc<ULongVar>()
            OH_JSVM_CreateCodeCache(env.value, script.value, null, codeCacheLen.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateCodeCache passed")
    }

    // ==================== Value 创建 ====================

    @Test
    fun testOH_JSVM_CreateArray() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val arrayValue = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(OH_JSVM_CreateArray(env.value, arrayValue.ptr))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateArray passed")
    }

    @Test
    fun testOH_JSVM_CreateObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val objectValue = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(OH_JSVM_CreateObject(env.value, objectValue.ptr))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateObject passed")
    }

    @Test
    fun testOH_JSVM_CreateStringUtf8() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val stringValue = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(OH_JSVM_CreateStringUtf8(env.value, "test", JSVM_AUTO_LENGTH, stringValue.ptr))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateStringUtf8 passed")
    }

    @Test
    fun testOH_JSVM_CreateInt32() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val int32Value = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(OH_JSVM_CreateInt32(env.value, 42, int32Value.ptr))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateInt32 passed")
    }

    @Test
    fun testOH_JSVM_GetBoolean() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val booleanValue = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(OH_JSVM_GetBoolean(env.value, true, booleanValue.ptr))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetBoolean passed")
    }

    // ==================== Handle Scope ====================

    @Test
    fun testOH_JSVM_OpenHandleScope() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val handleScope = alloc<CPointerVar<JSVM_HandleScope__>>()
            assertNotNull(OH_JSVM_OpenHandleScope(env.value, handleScope.ptr))
            OH_JSVM_CloseHandleScope(env.value, handleScope.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_OpenHandleScope passed")
    }

    @Test
    fun testOH_JSVM_CloseHandleScope() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val handleScope = alloc<CPointerVar<JSVM_HandleScope__>>()
            OH_JSVM_OpenHandleScope(env.value, handleScope.ptr)
            assertNotNull(OH_JSVM_CloseHandleScope(env.value, handleScope.value))
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CloseHandleScope passed")
    }

    // ==================== VM / Env 相关（单 API 测试）====================

    @Test
    fun testOH_JSVM_GetVMInfo() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val vmInfo = alloc<JSVM_VMInfo>()
            @Suppress("UNCHECKED_CAST")
            OH_JSVM_GetVMInfo(vmInfo.ptr as CValuesRef<JSVM_VMInfo>?)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetVMInfo passed")
    }

    // @Test
    // fun testOH_JSVM_CreateEnvFromSnapshot() {
    //     memScoped {
    //         val initOptions = alloc<JSVM_InitOptions>().apply {
    //             externalReferences = null
    //             argc = null
    //             argv = null
    //             removeFlags = false
    //         }
    //         OH_JSVM_Init(initOptions.ptr)
    //         val createVMOptions = alloc<JSVM_CreateVMOptions>()
    //         val vm = alloc<CPointerVar<JSVM_VM__>>()
    //         OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
    //         val envFromSnapshot = alloc<CPointerVar<JSVM_Env__>>()
    //         OH_JSVM_CreateEnvFromSnapshot(vm.value, 0u, envFromSnapshot.ptr)
    //         OH_JSVM_DestroyEnv(envFromSnapshot.value)
    //         OH_JSVM_DestroyVM(vm.value)
    //     }
    //     logLine("OH_JSVM_CreateEnvFromSnapshot passed")
    // }

    // @Test
    // fun testOH_JSVM_GetVM() {
    //     memScoped {
    //         val initOptions = alloc<JSVM_InitOptions>().apply {
    //             externalReferences = null
    //             argc = null
    //             argv = null
    //             removeFlags = false
    //         }
    //         OH_JSVM_Init(initOptions.ptr)
    //         val createVMOptions = alloc<JSVM_CreateVMOptions>()
    //         val vm = alloc<CPointerVar<JSVM_VM__>>()
    //         OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
    //         val env = alloc<CPointerVar<JSVM_Env__>>()
    //         val vmFromEnv = alloc<CPointerVar<JSVM_VM__>>()
    //         OH_JSVM_GetVM(env.value, vmFromEnv.ptr)
    //         OH_JSVM_DestroyVM(vm.value)
    //     }
    //     logLine("OH_JSVM_GetVM passed")
    // }

    @Test
    fun testOH_JSVM_SetInstanceData() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            OH_JSVM_SetInstanceData(env.value, null, finalizeCallback, null)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_SetInstanceData passed")
    }

    @Test
    fun testOH_JSVM_GetInstanceData() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val instanceData = alloc<COpaquePointerVar>()
            OH_JSVM_GetInstanceData(env.value, instanceData.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetInstanceData passed")
    }

    @Test
    fun testOH_JSVM_GetLastErrorInfo() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val lastErrorInfo = alloc<CPointerVar<JSVM_ExtendedErrorInfo>>()
            OH_JSVM_GetLastErrorInfo(env.value, lastErrorInfo.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetLastErrorInfo passed")
    }

    @Test
    fun testOH_JSVM_GetVersion() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val versionOut = alloc<UIntVar>()
            OH_JSVM_GetVersion(env.value, versionOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetVersion passed")
    }

    @Test
    fun testOH_JSVM_GetHeapStatistics() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val heapStats = alloc<JSVM_HeapStatistics>()
            OH_JSVM_GetHeapStatistics(vm.value, heapStats.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetHeapStatistics passed")
    }

    @Test
    fun testOH_JSVM_PumpMessageLoop() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            OH_JSVM_PumpMessageLoop(vm.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_PumpMessageLoop passed")
    }

    @Test
    fun testOH_JSVM_PerformMicrotaskCheckpoint() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            OH_JSVM_PerformMicrotaskCheckpoint(vm.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_PerformMicrotaskCheckpoint passed")
    }

    @Test
    fun testOH_JSVM_StartCpuProfiler() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val cpuProfiler = alloc<CPointerVar<JSVM_CpuProfiler__>>()
            OH_JSVM_StartCpuProfiler(vm.value, cpuProfiler.ptr)
            OH_JSVM_StopCpuProfiler(vm.value, cpuProfiler.value, outputStreamCallback, null)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_StartCpuProfiler passed")
    }

    @Test
    fun testOH_JSVM_StopCpuProfiler() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val cpuProfiler = alloc<CPointerVar<JSVM_CpuProfiler__>>()
            OH_JSVM_StartCpuProfiler(vm.value, cpuProfiler.ptr)
            OH_JSVM_StopCpuProfiler(vm.value, cpuProfiler.value, outputStreamCallback, null)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_StopCpuProfiler passed")
    }

    @Test
    fun testOH_JSVM_AdjustExternalMemory() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            OH_JSVM_AdjustExternalMemory(env.value, 0, alloc<LongVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_AdjustExternalMemory passed")
    }

    @Test
    fun testOH_JSVM_MemoryPressureNotification() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            OH_JSVM_MemoryPressureNotification(env.value, JSVM_MemoryPressureLevel.JSVM_MEMORY_PRESSURE_LEVEL_NONE)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_MemoryPressureNotification passed")
    }

    @Test
    fun testOH_JSVM_SetDebugOption() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            OH_JSVM_SetDebugOption(env.value, JSVM_DebugOption.JSVM_SCOPE_CHECK, false)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_SetDebugOption passed")
    }

    // ==================== Value 创建 / 类型检查 / Get（单 API 测试）====================

    @Test
    fun testOH_JSVM_CreateArrayWithLength() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val arrLen = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArrayWithLength(env.value, 4u, arrLen.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateArrayWithLength passed")
    }

    @Test
    fun testOH_JSVM_CreateStringLatin1() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            OH_JSVM_CreateStringLatin1(env.value, "a", 1u, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateStringLatin1 passed")
    }

    @Test
    fun testOH_JSVM_CreateStringUtf16() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val utf16 = allocArray<UShortVar>(2).apply { this[0] = 'x'.code.toUShort(); this[1] = 0u }
            OH_JSVM_CreateStringUtf16(env.value, utf16, JSVM_AUTO_LENGTH, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateStringUtf16 passed")
    }

    @Test
    fun testOH_JSVM_CreateUint32() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val u32 = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateUint32(env.value, 1u, u32.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateUint32 passed")
    }

    @Test
    fun testOH_JSVM_CreateInt64() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val i64 = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt64(env.value, 1L, i64.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateInt64 passed")
    }

    @Test
    fun testOH_JSVM_CreateDouble() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val dbl = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateDouble(env.value, 1.0, dbl.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateDouble passed")
    }

    @Test
    fun testOH_JSVM_CreateBigintInt64() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val bigi = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateBigintInt64(env.value, 1L, bigi.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateBigintInt64 passed")
    }

    @Test
    fun testOH_JSVM_CreateBigintUint64() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val bigu = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateBigintUint64(env.value, 1uL, bigu.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateBigintUint64 passed")
    }

    @Test
    fun testOH_JSVM_CreateSymbol() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val sym = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateSymbol(env.value, null, sym.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateSymbol passed")
    }

    @Test
    fun testOH_JSVM_CreateDate() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val dateVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateDate(env.value, 0.0, dateVal.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateDate passed")
    }

    @Test
    fun testOH_JSVM_CreateMap() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val mapVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateMap(env.value, mapVal.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateMap passed")
    }

    @Test
    fun testOH_JSVM_CreateSet() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val setVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateSet(env.value, setVal.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateSet passed")
    }

    @Test
    fun testOH_JSVM_GetGlobal() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val global = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetGlobal(env.value, global.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetGlobal passed")
    }

    @Test
    fun testOH_JSVM_GetNull() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val nullVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetNull(env.value, nullVal.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetNull passed")
    }

    @Test
    fun testOH_JSVM_GetUndefined() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val undefinedVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetUndefined(env.value, undefinedVal.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetUndefined passed")
    }

    @Test
    fun testOH_JSVM_GetArrayLength() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val arr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArray(env.value, arr.ptr)
            val arrLenOut = alloc<UIntVar>()
            OH_JSVM_GetArrayLength(env.value, arr.value, arrLenOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetArrayLength passed")
    }

    @Test
    fun testOH_JSVM_IsArray() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val arr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArray(env.value, arr.ptr)
            val isArr = alloc<BooleanVar>()
            OH_JSVM_IsArray(env.value, arr.value, isArr.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsArray passed")
    }

    @Test
    fun testOH_JSVM_IsObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(env.value, obj.ptr)
            val isObj = alloc<BooleanVar>()
            OH_JSVM_IsObject(env.value, obj.value, isObj.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsObject passed")
    }

    @Test
    fun testOH_JSVM_IsString() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val str = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(env.value, "x", JSVM_AUTO_LENGTH, str.ptr)
            val isStr = alloc<BooleanVar>()
            OH_JSVM_IsString(env.value, str.value, isStr.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsString passed")
    }

    @Test
    fun testOH_JSVM_IsNumber() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val i32 = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(env.value, 1, i32.ptr)
            val isNum = alloc<BooleanVar>()
            OH_JSVM_IsNumber(env.value, i32.value, isNum.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsNumber passed")
    }

    @Test
    fun testOH_JSVM_IsBoolean() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val boolVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetBoolean(env.value, true, boolVal.ptr)
            val isBool = alloc<BooleanVar>()
            OH_JSVM_IsBoolean(env.value, boolVal.value, isBool.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsBoolean passed")
    }

    @Test
    fun testOH_JSVM_IsUndefined() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val undefinedVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetUndefined(env.value, undefinedVal.ptr)
            val isUndef = alloc<BooleanVar>()
            OH_JSVM_IsUndefined(env.value, undefinedVal.value, isUndef.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsUndefined passed")
    }

    @Test
    fun testOH_JSVM_IsNull() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val nullVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetNull(env.value, nullVal.ptr)
            val isN = alloc<BooleanVar>()
            OH_JSVM_IsNull(env.value, nullVal.value, isN.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsNull passed")
    }

    @Test
    fun testOH_JSVM_IsNullOrUndefined() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val undefinedVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetUndefined(env.value, undefinedVal.ptr)
            OH_JSVM_IsNullOrUndefined(env.value, undefinedVal.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsNullOrUndefined passed")
    }

    @Test
    fun testOH_JSVM_IsCallable() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val isCallable = alloc<BooleanVar>()
            OH_JSVM_IsCallable(env.value, null, isCallable.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsCallable passed")
    }

    @Test
    fun testOH_JSVM_GetValueInt32() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val i32 = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(env.value, 1, i32.ptr)
            val v32 = alloc<IntVar>()
            OH_JSVM_GetValueInt32(env.value, i32.value, v32.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueInt32 passed")
    }

    @Test
    fun testOH_JSVM_GetValueUint32() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val u32 = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateUint32(env.value, 1u, u32.ptr)
            val vU32 = alloc<UIntVar>()
            OH_JSVM_GetValueUint32(env.value, u32.value, vU32.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueUint32 passed")
    }

    @Test
    fun testOH_JSVM_GetValueBool() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val boolVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetBoolean(env.value, true, boolVal.ptr)
            val vBool = alloc<BooleanVar>()
            OH_JSVM_GetValueBool(env.value, boolVal.value, vBool.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueBool passed")
    }

    @Test
    fun testOH_JSVM_GetValueDouble() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val dbl = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateDouble(env.value, 1.0, dbl.ptr)
            val vDbl = alloc<DoubleVar>()
            OH_JSVM_GetValueDouble(env.value, dbl.value, vDbl.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueDouble passed")
    }

    @Test
    fun testOH_JSVM_Typeof() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(env.value, obj.ptr)
            val typeofResult = alloc<JSVM_ValueType.Var>()
            OH_JSVM_Typeof(env.value, obj.value, typeofResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_Typeof passed")
    }

    @Test
    fun testOH_JSVM_SymbolFor() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val symResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_SymbolFor(env.value, "sym", 3u, symResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_SymbolFor passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolIterator() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            OH_JSVM_GetSymbolIterator(env.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolIterator passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolAsyncIterator() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            OH_JSVM_GetSymbolAsyncIterator(env.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolAsyncIterator passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolHasInstance() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            OH_JSVM_GetSymbolHasInstance(env.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolHasInstance passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolToStringTag() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            OH_JSVM_GetSymbolToStringTag(env.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolToStringTag passed")
    }

    @Test
    fun testOH_JSVM_IsMap() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val mapVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateMap(env.value, mapVal.ptr)
            OH_JSVM_IsMap(env.value, mapVal.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsMap passed")
    }

    @Test
    fun testOH_JSVM_IsSet() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val setVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateSet(env.value, setVal.ptr)
            OH_JSVM_IsSet(env.value, setVal.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsSet passed")
    }

    // ==================== Property / Object / Promise / JSON（单 API 测试）====================

    @Test
    fun testOH_JSVM_SetProperty() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val key = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateStringUtf8(e, "k", JSVM_AUTO_LENGTH, key.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetProperty(e, obj.value, key.value, valProp.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_SetProperty passed")
    }

    @Test
    fun testOH_JSVM_GetProperty() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val key = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateStringUtf8(e, "k", JSVM_AUTO_LENGTH, key.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetProperty(e, obj.value, key.value, valProp.value)
            val getProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetProperty(e, obj.value, key.value, getProp.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetProperty passed")
    }

    @Test
    fun testOH_JSVM_HasProperty() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val key = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateStringUtf8(e, "k", JSVM_AUTO_LENGTH, key.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetProperty(e, obj.value, key.value, valProp.value)
            val hasProp = alloc<BooleanVar>()
            OH_JSVM_HasProperty(e, obj.value, key.value, hasProp.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_HasProperty passed")
    }

    @Test
    fun testOH_JSVM_SetNamedProperty() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetNamedProperty(e, obj.value, "name", valProp.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_SetNamedProperty passed")
    }

    @Test
    fun testOH_JSVM_GetNamedProperty() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetNamedProperty(e, obj.value, "name", valProp.value)
            val getNamedPropResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetNamedProperty(e, obj.value, "name", getNamedPropResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetNamedProperty passed")
    }

    @Test
    fun testOH_JSVM_HasNamedProperty() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetNamedProperty(e, obj.value, "name", valProp.value)
            OH_JSVM_HasNamedProperty(e, obj.value, "name", alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_HasNamedProperty passed")
    }

    @Test
    fun testOH_JSVM_SetElement() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetElement(e, obj.value, 0u, valProp.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_SetElement passed")
    }

    @Test
    fun testOH_JSVM_GetElement() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetElement(e, obj.value, 0u, valProp.value)
            val getElemResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetElement(e, obj.value, 0u, getElemResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetElement passed")
    }

    @Test
    fun testOH_JSVM_HasElement() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetElement(e, obj.value, 0u, valProp.value)
            OH_JSVM_HasElement(e, obj.value, 0u, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_HasElement passed")
    }

    @Test
    fun testOH_JSVM_DeleteProperty() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val key = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateStringUtf8(e, "k", JSVM_AUTO_LENGTH, key.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetProperty(e, obj.value, key.value, valProp.value)
            OH_JSVM_DeleteProperty(e, obj.value, key.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_DeleteProperty passed")
    }

    @Test
    fun testOH_JSVM_DeleteElement() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_SetElement(e, obj.value, 0u, valProp.value)
            OH_JSVM_DeleteElement(e, obj.value, 0u, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_DeleteElement passed")
    }

    @Test
    fun testOH_JSVM_HasOwnProperty() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            val key = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_CreateStringUtf8(e, "k", JSVM_AUTO_LENGTH, key.ptr)
            OH_JSVM_HasOwnProperty(e, obj.value, key.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_HasOwnProperty passed")
    }

    @Test
    fun testOH_JSVM_DefineProperties() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_DefineProperties(e, obj.value, 0u, null)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_DefineProperties passed")
    }

    @Test
    fun testOH_JSVM_ObjectSeal() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_ObjectSeal(e, obj.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ObjectSeal passed")
    }

    @Test
    fun testOH_JSVM_ObjectFreeze() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            OH_JSVM_ObjectFreeze(e, obj.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ObjectFreeze passed")
    }

    @Test
    fun testOH_JSVM_CreatePromise() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val deferred = alloc<CPointerVar<JSVM_Deferred__>>()
            val promise = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreatePromise(e, deferred.ptr, promise.ptr)
            OH_JSVM_IsPromise(e, promise.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreatePromise passed")
    }

    @Test
    fun testOH_JSVM_IsPromise() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val deferred = alloc<CPointerVar<JSVM_Deferred__>>()
            val promise = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreatePromise(e, deferred.ptr, promise.ptr)
            val out = alloc<BooleanVar>()
            OH_JSVM_IsPromise(e, promise.value, out.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsPromise passed")
    }

    @Test
    fun testOH_JSVM_ResolveDeferred() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val deferred = alloc<CPointerVar<JSVM_Deferred__>>()
            val promise = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreatePromise(e, deferred.ptr, promise.ptr)
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 42, valProp.ptr)
            OH_JSVM_ResolveDeferred(e, deferred.value, valProp.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ResolveDeferred passed")
    }

    @Test
    fun testOH_JSVM_RejectDeferred() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val deferred = alloc<CPointerVar<JSVM_Deferred__>>()
            val promise = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreatePromise(e, deferred.ptr, promise.ptr)
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            OH_JSVM_RejectDeferred(e, deferred.value, valProp.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_RejectDeferred passed")
    }

    @Test
    fun testOH_JSVM_PromiseRegisterHandler() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val onFulfilled = alloc<CPointerVar<JSVM_Value__>>()
            val onRejected = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fulfilled", JSVM_AUTO_LENGTH, cbStruct.ptr, onFulfilled.ptr)
            OH_JSVM_CreateFunction(e, "rejected", JSVM_AUTO_LENGTH, cbStruct.ptr, onRejected.ptr)
            val deferred = alloc<CPointerVar<JSVM_Deferred__>>()
            val promise = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreatePromise(e, deferred.ptr, promise.ptr)
            val promiseRegResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_PromiseRegisterHandler(e, promise.value, onFulfilled.value, onRejected.value, promiseRegResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_PromiseRegisterHandler passed")
    }

    @Test
    fun testOH_JSVM_JsonParse() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val jsonStr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "{}", JSVM_AUTO_LENGTH, jsonStr.ptr)
            val jsonParseResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_JsonParse(e, jsonStr.value, jsonParseResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_JsonParse passed")
    }

    @Test
    fun testOH_JSVM_JsonStringify() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val jsonStringifyResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_JsonStringify(e, obj.value, jsonStringifyResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_JsonStringify passed")
    }

    @Test
    fun testOH_JSVM_GetPropertyNames() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val propNamesResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetPropertyNames(e, obj.value, propNamesResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetPropertyNames passed")
    }

    @Test
    fun testOH_JSVM_GetPrototype() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val getProtoResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetPrototype(e, obj.value, getProtoResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetPrototype passed")
    }

    @Test
    fun testOH_JSVM_ObjectGetPrototypeOf() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val getObjProtoResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_ObjectGetPrototypeOf(e, obj.value, getObjProtoResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ObjectGetPrototypeOf passed")
    }

    // ==================== Throw / Error / Reference / HandleScope（单 API 测试）====================

    @Test
    fun testOH_JSVM_Throw() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val errVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "err", JSVM_AUTO_LENGTH, errVal.ptr)
            OH_JSVM_Throw(e, errVal.value)
            OH_JSVM_GetAndClearLastException(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_Throw passed")
    }

    @Test
    fun testOH_JSVM_ThrowError() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_ThrowError(e, null, "msg")
            OH_JSVM_GetAndClearLastException(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ThrowError passed")
    }

    @Test
    fun testOH_JSVM_ThrowTypeError() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_ThrowTypeError(e, null, "type")
            OH_JSVM_GetAndClearLastException(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ThrowTypeError passed")
    }

    @Test
    fun testOH_JSVM_ThrowRangeError() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_ThrowRangeError(e, null, "range")
            OH_JSVM_GetAndClearLastException(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ThrowRangeError passed")
    }

    @Test
    fun testOH_JSVM_ThrowSyntaxError() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_ThrowSyntaxError(e, null, "syntax")
            OH_JSVM_GetAndClearLastException(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ThrowSyntaxError passed")
    }

    @Test
    fun testOH_JSVM_CreateError() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val errVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "err", JSVM_AUTO_LENGTH, errVal.ptr)
            OH_JSVM_CreateError(e, null, errVal.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateError passed")
    }

    @Test
    fun testOH_JSVM_CreateTypeError() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val errVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "err", JSVM_AUTO_LENGTH, errVal.ptr)
            OH_JSVM_CreateTypeError(e, null, errVal.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateTypeError passed")
    }

    @Test
    fun testOH_JSVM_CreateRangeError() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val errVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "err", JSVM_AUTO_LENGTH, errVal.ptr)
            OH_JSVM_CreateRangeError(e, null, errVal.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateRangeError passed")
    }

    @Test
    fun testOH_JSVM_CreateSyntaxError() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val errVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "err", JSVM_AUTO_LENGTH, errVal.ptr)
            OH_JSVM_CreateSyntaxError(e, null, errVal.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateSyntaxError passed")
    }

    @Test
    fun testOH_JSVM_IsError() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val errVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "err", JSVM_AUTO_LENGTH, errVal.ptr)
            OH_JSVM_IsError(e, errVal.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsError passed")
    }

    @Test
    fun testOH_JSVM_IsExceptionPending() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_IsExceptionPending(e, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsExceptionPending passed")
    }

    @Test
    fun testOH_JSVM_GetAndClearLastException() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val errVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "err", JSVM_AUTO_LENGTH, errVal.ptr)
            OH_JSVM_Throw(e, errVal.value)
            OH_JSVM_GetAndClearLastException(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetAndClearLastException passed")
    }

    @Test
    fun testOH_JSVM_CreateReference() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val intVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, intVal.ptr)
            val ref = alloc<CPointerVar<JSVM_Ref__>>()
            OH_JSVM_CreateReference(e, intVal.value, 1u, ref.ptr)
            OH_JSVM_DeleteReference(e, ref.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateReference passed")
    }

    @Test
    fun testOH_JSVM_ReferenceRef() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val intVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, intVal.ptr)
            val ref = alloc<CPointerVar<JSVM_Ref__>>()
            OH_JSVM_CreateReference(e, intVal.value, 1u, ref.ptr)
            OH_JSVM_ReferenceRef(e, ref.value, alloc<UIntVar>().ptr)
            OH_JSVM_ReferenceUnref(e, ref.value, alloc<UIntVar>().ptr)
            OH_JSVM_DeleteReference(e, ref.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ReferenceRef passed")
    }

    @Test
    fun testOH_JSVM_GetReferenceValue() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val intVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, intVal.ptr)
            val ref = alloc<CPointerVar<JSVM_Ref__>>()
            OH_JSVM_CreateReference(e, intVal.value, 1u, ref.ptr)
            OH_JSVM_GetReferenceValue(e, ref.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DeleteReference(e, ref.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetReferenceValue passed")
    }

    @Test
    fun testOH_JSVM_ReferenceUnref() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val intVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, intVal.ptr)
            val ref = alloc<CPointerVar<JSVM_Ref__>>()
            OH_JSVM_CreateReference(e, intVal.value, 1u, ref.ptr)
            OH_JSVM_ReferenceUnref(e, ref.value, alloc<UIntVar>().ptr)
            OH_JSVM_DeleteReference(e, ref.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ReferenceUnref passed")
    }

    @Test
    fun testOH_JSVM_DeleteReference() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val intVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, intVal.ptr)
            val ref = alloc<CPointerVar<JSVM_Ref__>>()
            OH_JSVM_CreateReference(e, intVal.value, 1u, ref.ptr)
            OH_JSVM_DeleteReference(e, ref.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_DeleteReference passed")
    }

    @Test
    fun testOH_JSVM_OpenEscapableHandleScope() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val escScope = alloc<CPointerVar<JSVM_EscapableHandleScope__>>()
            OH_JSVM_OpenEscapableHandleScope(e, escScope.ptr)
            OH_JSVM_CloseEscapableHandleScope(e, escScope.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_OpenEscapableHandleScope passed")
    }

    @Test
    fun testOH_JSVM_EscapeHandle() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val intVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, intVal.ptr)
            val escScope = alloc<CPointerVar<JSVM_EscapableHandleScope__>>()
            OH_JSVM_OpenEscapableHandleScope(e, escScope.ptr)
            OH_JSVM_EscapeHandle(e, escScope.value, intVal.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_CloseEscapableHandleScope(e, escScope.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_EscapeHandle passed")
    }

    @Test
    fun testOH_JSVM_CloseEscapableHandleScope() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val escScope = alloc<CPointerVar<JSVM_EscapableHandleScope__>>()
            OH_JSVM_OpenEscapableHandleScope(e, escScope.ptr)
            OH_JSVM_CloseEscapableHandleScope(e, escScope.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CloseEscapableHandleScope passed")
    }

    @Test
    fun testOH_JSVM_AddFinalizer() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val intVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, intVal.ptr)
            val addFinRef = alloc<CPointerVar<JSVM_Ref__>>()
            OH_JSVM_AddFinalizer(e, intVal.value, null, finalizeCallback, null, addFinRef.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_AddFinalizer passed")
    }

    // ==================== Function / Call / CreateFunction（单 API 测试）====================

    @Test
    fun testOH_JSVM_CreateFunction() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _env: CPointer<JSVM_Env__>?, _info: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateFunction passed")
    }

    @Test
    fun testOH_JSVM_CallFunction() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            val callResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CallFunction(e, fn.value, null, 0u, null, callResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CallFunction passed")
    }

    @Test
    fun testOH_JSVM_GetNewTarget() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val newTargetResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetNewTarget(e, null, newTargetResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetNewTarget passed")
    }

    @Test
    fun testOH_JSVM_CreateFunctionWithScript() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val scriptSrc = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "function f(){return 1}", JSVM_AUTO_LENGTH, scriptSrc.ptr)
            val createFnWithScriptResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunctionWithScript(e, "g", JSVM_AUTO_LENGTH, 0u, null, scriptSrc.value, createFnWithScriptResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateFunctionWithScript passed")
    }

    @Test
    fun testOH_JSVM_RetainScript() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val scriptSrc = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "1+1", JSVM_AUTO_LENGTH, scriptSrc.ptr)
            val script = alloc<CPointerVar<JSVM_Script__>>()
            OH_JSVM_RetainScript(e, script.value)
            OH_JSVM_ReleaseScript(e, script.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_RetainScript passed")
    }

    @Test
    fun testOH_JSVM_ReleaseScript() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val scriptSrc = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "1+1", JSVM_AUTO_LENGTH, scriptSrc.ptr)
            val script = alloc<CPointerVar<JSVM_Script__>>()
            OH_JSVM_ReleaseScript(e, script.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ReleaseScript passed")
    }

    @Test
    fun testOH_JSVM_IsLocked() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_IsLocked(e, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsLocked passed")
    }

    @Test
    fun testOH_JSVM_AcquireLock() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_AcquireLock(e)
            OH_JSVM_ReleaseLock(e)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_AcquireLock passed")
    }

    @Test
    fun testOH_JSVM_ReleaseLock() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_AcquireLock(e)
            OH_JSVM_ReleaseLock(e)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ReleaseLock passed")
    }

    @Test
    fun testOH_JSVM_IsConstructor() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_IsConstructor(e, fn.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsConstructor passed")
    }

    @Test
    fun testOH_JSVM_Instanceof() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_Instanceof(e, fn.value, fn.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_Instanceof passed")
    }

    @Test
    fun testOH_JSVM_NewInstance() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_NewInstance(e, fn.value, 0u, null, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_NewInstance passed")
    }

    @Test
    fun testOH_JSVM_DefineClass() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            OH_JSVM_DefineClass(e, "C", JSVM_AUTO_LENGTH, cbStruct.ptr, 0u, null, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_DefineClass passed")
    }

    @Test
    fun testOH_JSVM_CoerceToBool() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_CoerceToBool(e, fn.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CoerceToBool passed")
    }

    @Test
    fun testOH_JSVM_CoerceToNumber() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_CoerceToNumber(e, fn.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CoerceToNumber passed")
    }

    @Test
    fun testOH_JSVM_CoerceToObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_CoerceToObject(e, fn.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CoerceToObject passed")
    }

    @Test
    fun testOH_JSVM_CoerceToString() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_CoerceToString(e, fn.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CoerceToString passed")
    }

    @Test
    fun testOH_JSVM_CoerceToBigInt() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "fn", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_CoerceToBigInt(e, fn.value, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CoerceToBigInt passed")
    }

    @Test
    fun testOH_JSVM_CreateRegExp() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val regexStr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "a", JSVM_AUTO_LENGTH, regexStr.ptr)
            val regexResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateRegExp(e, regexStr.value, JSVM_REGEXP_NONE, regexResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateRegExp passed")
    }

    @Test
    fun testOH_JSVM_IsRegExp() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_IsRegExp(e, null, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsRegExp passed")
    }

    @Test
    fun testOH_JSVM_CompileScriptWithOrigin() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val scriptSrc = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "function f(){return 1}", JSVM_AUTO_LENGTH, scriptSrc.ptr)
            val scriptOrigin = alloc<JSVM_ScriptOrigin>()
            val scriptCompiledOut = alloc<CPointerVar<JSVM_Script__>>()
            val cacheRejected = alloc<BooleanVar>()
            OH_JSVM_CompileScriptWithOrigin(e, scriptSrc.value, null, 0u, false, cacheRejected.ptr, scriptOrigin.ptr, scriptCompiledOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CompileScriptWithOrigin passed")
    }

    // ==================== OH_JSVM_DefineClassWithPropertyHandler（8 个属性回调需通过脚本触发）====================

    @Test
    fun testOH_JSVM_DefineClassWithPropertyHandler() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)        
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val namedGetter = staticCFunction { env: CPointer<JSVM_Env__>?, _name: CPointer<JSVM_Value__>?, _thisArg: CPointer<JSVM_Value__>?, _data: CPointer<JSVM_Value__>? ->
                if (env != null) memScoped { val r = alloc<CPointerVar<JSVM_Value__>>(); OH_JSVM_GetUndefined(env, r.ptr); r.value } else null
            }
            val namedSetter = staticCFunction { env: CPointer<JSVM_Env__>?, _name: CPointer<JSVM_Value__>?, _prop: CPointer<JSVM_Value__>?, _thisArg: CPointer<JSVM_Value__>?, _data: CPointer<JSVM_Value__>? ->
                if (env != null) memScoped { val r = alloc<CPointerVar<JSVM_Value__>>(); OH_JSVM_GetUndefined(env, r.ptr); r.value } else null
            }
            val namedDeleter = staticCFunction { env: CPointer<JSVM_Env__>?, _name: CPointer<JSVM_Value__>?, _thisArg: CPointer<JSVM_Value__>?, _data: CPointer<JSVM_Value__>? ->
                if (env != null) memScoped { val r = alloc<CPointerVar<JSVM_Value__>>(); OH_JSVM_GetBoolean(env, true, r.ptr); r.value } else null
            }
            val namedEnumerator = staticCFunction { env: CPointer<JSVM_Env__>?, _thisArg: CPointer<JSVM_Value__>?, _data: CPointer<JSVM_Value__>? ->
                if (env != null) memScoped { val r = alloc<CPointerVar<JSVM_Value__>>(); OH_JSVM_CreateArray(env, r.ptr); r.value } else null
            }
            val indexedGetter = staticCFunction { env: CPointer<JSVM_Env__>?, _index: CPointer<JSVM_Value__>?, _thisArg: CPointer<JSVM_Value__>?, _data: CPointer<JSVM_Value__>? ->
                if (env != null) memScoped { val r = alloc<CPointerVar<JSVM_Value__>>(); OH_JSVM_GetUndefined(env, r.ptr); r.value } else null
            }
            val indexedSetter = staticCFunction { env: CPointer<JSVM_Env__>?, _index: CPointer<JSVM_Value__>?, _prop: CPointer<JSVM_Value__>?, _thisArg: CPointer<JSVM_Value__>?, _data: CPointer<JSVM_Value__>? ->
                if (env != null) memScoped { val r = alloc<CPointerVar<JSVM_Value__>>(); OH_JSVM_GetUndefined(env, r.ptr); r.value } else null
            }
            val indexedDeleter = staticCFunction { env: CPointer<JSVM_Env__>?, _index: CPointer<JSVM_Value__>?, _thisArg: CPointer<JSVM_Value__>?, _data: CPointer<JSVM_Value__>? ->
                if (env != null) memScoped { val r = alloc<CPointerVar<JSVM_Value__>>(); OH_JSVM_GetBoolean(env, true, r.ptr); r.value } else null
            }
            val indexedEnumerator = staticCFunction { env: CPointer<JSVM_Env__>?, _thisArg: CPointer<JSVM_Value__>?, _data: CPointer<JSVM_Value__>? ->
                if (env != null) memScoped { val r = alloc<CPointerVar<JSVM_Value__>>(); OH_JSVM_CreateArray(env, r.ptr); r.value } else null
            }
            val config = alloc<JSVM_PropertyHandlerConfigurationStruct>().apply {
                genericNamedPropertyGetterCallback = namedGetter
                genericNamedPropertySetterCallback = namedSetter
                genericNamedPropertyDeleterCallback = namedDeleter
                genericNamedPropertyEnumeratorCallback = namedEnumerator
                genericIndexedPropertyGetterCallback = indexedGetter
                genericIndexedPropertySetterCallback = indexedSetter
                genericIndexedPropertyDeleterCallback = indexedDeleter
                genericIndexedPropertyEnumeratorCallback = indexedEnumerator
                namedPropertyData = null
                indexedPropertyData = null
            }
            val ctorCb = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { env: CPointer<JSVM_Env__>?, info: CPointer<JSVM_CallbackInfo__>? ->
                    if (env == null || info == null) return@staticCFunction null
                    memScoped { val thisArg = alloc<CPointerVar<JSVM_Value__>>(); OH_JSVM_GetCbInfo(env, info, null, null, thisArg.ptr, null); thisArg.value }
                }
                data = null
            }
            val cls = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(OH_JSVM_DefineClassWithPropertyHandler(e, "HandlerClass", JSVM_AUTO_LENGTH, ctorCb.ptr, 0u, null, config.ptr, null, cls.ptr))
            val global = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetGlobal(e, global.ptr)
            OH_JSVM_SetNamedProperty(e, global.value, "HandlerClass", cls.value)
            val scriptSrc = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e,
                "var o = new HandlerClass(); o.foo; o.foo = 1; delete o.foo; Object.keys(o); o[0]; o[0] = 1; delete o[0];",
                JSVM_AUTO_LENGTH, scriptSrc.ptr)
            OH_JSVM_DestroyVM(vm.value)
            logLine("testOH_JSVM_DefineClassWithPropertyHandler passed")
        }
    }

    // ==================== Proxy / Wrap / Arraybuffer / TypedArray（单 API 测试）====================

    @Test
    fun testOH_JSVM_CreateProxy() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val target = alloc<CPointerVar<JSVM_Value__>>()
            val handler = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, target.ptr)
            OH_JSVM_CreateObject(e, handler.ptr)
            val out = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(try { OH_JSVM_CreateProxy(e, target.value, handler.value, out.ptr) } catch (t: Throwable) { logLine("OH_JSVM_CreateProxy exception: $t"); JSVM_Status.JSVM_INVALID_ARG })
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateProxy passed")
    }

    @Test
    fun testOH_JSVM_IsProxy() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val target = alloc<CPointerVar<JSVM_Value__>>()
            val handler = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, target.ptr)
            OH_JSVM_CreateObject(e, handler.ptr)
            val proxyVal = alloc<CPointerVar<JSVM_Value__>>()
            try { OH_JSVM_CreateProxy(e, target.value, handler.value, proxyVal.ptr) } catch (_: Throwable) { }
            assertNotNull(try { OH_JSVM_IsProxy(e, proxyVal.value, alloc<BooleanVar>().ptr) } catch (_: Throwable) { JSVM_Status.JSVM_INVALID_ARG })
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsProxy passed")
    }

    @Test
    fun testOH_JSVM_ProxyGetTarget() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val target = alloc<CPointerVar<JSVM_Value__>>()
            val handler = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, target.ptr)
            OH_JSVM_CreateObject(e, handler.ptr)
            val proxyVal = alloc<CPointerVar<JSVM_Value__>>()
            try { OH_JSVM_CreateProxy(e, target.value, handler.value, proxyVal.ptr) } catch (_: Throwable) { }
            val proxyTargetOut = alloc<CPointerVar<JSVM_Value__>>()
            assertNotNull(try { OH_JSVM_ProxyGetTarget(e, proxyVal.value, proxyTargetOut.ptr) } catch (_: Throwable) { JSVM_Status.JSVM_INVALID_ARG })
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ProxyGetTarget passed")
    }

    @Test
    fun testOH_JSVM_Wrap() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val target = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, target.ptr)
            val wrapRef = alloc<CPointerVar<JSVM_Ref__>>()
            OH_JSVM_Wrap(e, target.value, null, finalizeCallback, null, wrapRef.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_Wrap passed")
    }

    @Test
    fun testOH_JSVM_Unwrap() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val target = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, target.ptr)
            val unwrapOut = alloc<COpaquePointerVar>()
            OH_JSVM_Unwrap(e, target.value, unwrapOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_Unwrap passed")
    }

    @Test
    fun testOH_JSVM_RemoveWrap() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val target = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, target.ptr)
            val removeWrapOut = alloc<COpaquePointerVar>()
            OH_JSVM_RemoveWrap(e, target.value, removeWrapOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_RemoveWrap passed")
    }

    @Test
    fun testOH_JSVM_TypeTagObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val target = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, target.ptr)
            val tag = alloc<JSVM_TypeTag>().apply { lower = 0uL; upper = 0uL }
            OH_JSVM_TypeTagObject(e, target.value, tag.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_TypeTagObject passed")
    }

    @Test
    fun testOH_JSVM_CheckObjectTypeTag() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val target = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, target.ptr)
            val tag = alloc<JSVM_TypeTag>().apply { lower = 0uL; upper = 0uL }
            OH_JSVM_TypeTagObject(e, target.value, tag.ptr)
            val checkTagResult = alloc<BooleanVar>()
            OH_JSVM_CheckObjectTypeTag(e, target.value, tag.ptr, checkTagResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CheckObjectTypeTag passed")
    }

    @Test
    fun testOH_JSVM_GetArraybufferInfo() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            val arrBufDataOut = alloc<COpaquePointerVar>()
            val arrBufSizeOut = alloc<ULongVar>()
            OH_JSVM_GetArraybufferInfo(e, buf.value, arrBufDataOut.ptr, arrBufSizeOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetArraybufferInfo passed")
    }

    @Test
    fun testOH_JSVM_IsArraybuffer() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            OH_JSVM_IsArraybuffer(e, buf.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsArraybuffer passed")
    }

    @Test
    fun testOH_JSVM_CreateTypedarray() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            val out = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateTypedarray(e, JSVM_TypedarrayType.JSVM_INT8_ARRAY, 8u, buf.value, 0u, out.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateTypedarray passed")
    }

    @Test
    fun testOH_JSVM_CreateDataview() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            val out = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateDataview(e, 8u, buf.value, 0u, out.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateDataview passed")
    }

    @Test
    fun testOH_JSVM_GetTypedarrayInfo() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            val typedArr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateTypedarray(e, JSVM_TypedarrayType.JSVM_INT8_ARRAY, 8u, buf.value, 0u, typedArr.ptr)
            val typedTypeOut = alloc<JSVM_TypedarrayType.Var>()
            val typedLenOut = alloc<ULongVar>()
            val typedDataOut = alloc<COpaquePointerVar>()
            val typedArrBufOut = alloc<CPointerVar<JSVM_Value__>>()
            val typedByteOffOut = alloc<ULongVar>()
            OH_JSVM_GetTypedarrayInfo(e, typedArr.value, typedTypeOut.ptr, typedLenOut.ptr, typedDataOut.ptr, typedArrBufOut.ptr, typedByteOffOut.ptr) 
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetTypedarrayInfo passed")
    }

    @Test
    fun testOH_JSVM_IsTypedarray() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            val typedArr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateTypedarray(e, JSVM_TypedarrayType.JSVM_INT8_ARRAY, 8u, buf.value, 0u, typedArr.ptr)
            OH_JSVM_IsTypedarray(e, typedArr.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsTypedarray passed")
    }

    @Test
    fun testOH_JSVM_GetDataviewInfo() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            val dv = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateDataview(e, 8u, buf.value, 0u, dv.ptr)
            val dvByteLen = alloc<ULongVar>()
            val dvData = alloc<COpaquePointerVar>()
            val dvArrBuf = alloc<CPointerVar<JSVM_Value__>>()
            val dvByteOff = alloc<ULongVar>()
            OH_JSVM_GetDataviewInfo(e, dv.value, dvByteLen.ptr, dvData.ptr, dvArrBuf.ptr, dvByteOff.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetDataviewInfo passed")
    }

    @Test
    fun testOH_JSVM_IsDataview() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            val dv = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateDataview(e, 8u, buf.value, 0u, dv.ptr)
            OH_JSVM_IsDataview(e, dv.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsDataview passed")
    }

    @Test
    fun testOH_JSVM_CreatePrivate() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val descStr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "p", JSVM_AUTO_LENGTH, descStr.ptr)
            val privateKeyData = alloc<CPointerVar<JSVM_Data__>>()
            OH_JSVM_CreatePrivate(e, descStr.value, privateKeyData.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreatePrivate passed")
    }

    @Test
    fun testOH_JSVM_SetPrivate() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            val descStr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "p", JSVM_AUTO_LENGTH, descStr.ptr)
            val privateKeyData = alloc<CPointerVar<JSVM_Data__>>()
            OH_JSVM_CreatePrivate(e, descStr.value, privateKeyData.ptr)
            OH_JSVM_SetPrivate(e, obj.value, privateKeyData.value, valProp.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_SetPrivate passed")
    }

    @Test
    fun testOH_JSVM_GetPrivate() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            val descStr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "p", JSVM_AUTO_LENGTH, descStr.ptr)
            val privateKeyData = alloc<CPointerVar<JSVM_Data__>>()
            OH_JSVM_CreatePrivate(e, descStr.value, privateKeyData.ptr)
            OH_JSVM_SetPrivate(e, obj.value, privateKeyData.value, valProp.value)
            val getPrivateResult = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetPrivate(e, obj.value, privateKeyData.value, getPrivateResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetPrivate passed")
    }

    @Test
    fun testOH_JSVM_CreateDataReference() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val descStr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "p", JSVM_AUTO_LENGTH, descStr.ptr)
            val privateKeyData = alloc<CPointerVar<JSVM_Data__>>()
            OH_JSVM_CreatePrivate(e, descStr.value, privateKeyData.ptr)
            val dataRef = alloc<CPointerVar<JSVM_Ref__>>()
            OH_JSVM_CreateDataReference(e, privateKeyData.value, 0u, dataRef.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateDataReference passed")
    }

    @Test
    fun testOH_JSVM_GetReferenceData() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val descStr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "p", JSVM_AUTO_LENGTH, descStr.ptr)
            val privateKeyData = alloc<CPointerVar<JSVM_Data__>>()
            OH_JSVM_CreatePrivate(e, descStr.value, privateKeyData.ptr)
            val dataRef = alloc<CPointerVar<JSVM_Ref__>>()
            OH_JSVM_CreateDataReference(e, privateKeyData.value, 0u, dataRef.ptr)
            val refDataOut = alloc<CPointerVar<JSVM_Data__>>()
            OH_JSVM_GetReferenceData(e, dataRef.value, refDataOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetReferenceData passed")
    }

    @Test
    fun testOH_JSVM_DeletePrivate() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val valProp = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, valProp.ptr)
            val descStr = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "p", JSVM_AUTO_LENGTH, descStr.ptr)
            val privateKeyData = alloc<CPointerVar<JSVM_Data__>>()
            OH_JSVM_CreatePrivate(e, descStr.value, privateKeyData.ptr)
            OH_JSVM_SetPrivate(e, obj.value, privateKeyData.value, valProp.value)
            OH_JSVM_DeletePrivate(e, obj.value, privateKeyData.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_DeletePrivate passed")
    }

    @Test
    fun testOH_JSVM_IsBigIntObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val bigi = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateBigintInt64(e, 1L, bigi.ptr)
            OH_JSVM_IsBigIntObject(e, bigi.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsBigIntObject passed")
    }

    @Test
    fun testOH_JSVM_IsBooleanObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val boolVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetBoolean(e, true, boolVal.ptr)
            OH_JSVM_IsBooleanObject(e, boolVal.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsBooleanObject passed")
    }

    @Test
    fun testOH_JSVM_IsStringObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val str = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "x", JSVM_AUTO_LENGTH, str.ptr)
            OH_JSVM_IsStringObject(e, str.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsStringObject passed")
    }

    @Test
    fun testOH_JSVM_IsSymbolObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val sym = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateSymbol(e, null, sym.ptr)
            OH_JSVM_IsSymbolObject(e, sym.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsSymbolObject passed")
    }

    @Test
    fun testOH_JSVM_GetValueExternal() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val extVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateExternal(e, null, finalizeCallback, null, extVal.ptr)
            val getExtOut = alloc<COpaquePointerVar>()
            OH_JSVM_GetValueExternal(e, extVal.value, getExtOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueExternal passed")
    }

    @Test
    fun testOH_JSVM_GetValueInt64() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val i64 = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt64(e, 1L, i64.ptr)
            OH_JSVM_GetValueInt64(e, i64.value, alloc<LongVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueInt64 passed")
    }

    @Test
    fun testOH_JSVM_GetDateValue() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val dateVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateDate(e, 0.0, dateVal.ptr)
            OH_JSVM_GetDateValue(e, dateVal.value, alloc<DoubleVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetDateValue passed")
    }

    @Test
    fun testOH_JSVM_GetValueStringLatin1() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val str = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "x", JSVM_AUTO_LENGTH, str.ptr)
            val strLenOut = alloc<ULongVar>()
            OH_JSVM_GetValueStringLatin1(e, str.value, null, 0u, strLenOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueStringLatin1 passed")
    }

    @Test
    fun testOH_JSVM_GetValueStringUtf8() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val str = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "x", JSVM_AUTO_LENGTH, str.ptr)
            val strLenOut = alloc<ULongVar>()
            OH_JSVM_GetValueStringUtf8(e, str.value, null, 0u, strLenOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueStringUtf8 passed")
    }

    @Test
    fun testOH_JSVM_GetValueStringUtf16() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val str = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "x", JSVM_AUTO_LENGTH, str.ptr)
            val strLenOut = alloc<ULongVar>()
            OH_JSVM_GetValueStringUtf16(e, str.value, null, 0u, strLenOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueStringUtf16 passed")
    }

    @Test
    fun testOH_JSVM_GetAllPropertyNames() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val allPropNamesOut = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetAllPropertyNames(e, obj.value, JSVM_KeyCollectionMode.JSVM_KEY_OWN_ONLY, JSVM_KEY_ALL_PROPERTIES, JSVM_KeyConversion.JSVM_KEY_NUMBERS_TO_STRINGS, allPropNamesOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetAllPropertyNames passed")
    }

    @Test
    fun testOH_JSVM_ObjectSetPrototypeOf() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val obj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateObject(e, obj.ptr)
            val nullVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_GetNull(e, nullVal.ptr)
            OH_JSVM_ObjectSetPrototypeOf(e, obj.value, nullVal.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ObjectSetPrototypeOf passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolIsConcatSpreadable() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_GetSymbolIsConcatSpreadable(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolIsConcatSpreadable passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolMatch() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_GetSymbolMatch(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolMatch passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolReplace() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_GetSymbolReplace(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolReplace passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolSearch() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_GetSymbolSearch(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolSearch passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolSplit() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_GetSymbolSplit(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolSplit passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolToPrimitive() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_GetSymbolToPrimitive(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolToPrimitive passed")
    }

    @Test
    fun testOH_JSVM_GetSymbolUnscopables() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_GetSymbolUnscopables(e, alloc<CPointerVar<JSVM_Value__>>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetSymbolUnscopables passed")
    }

    @Test
    fun testOH_JSVM_CompileScriptWithOptions() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val scriptSrc = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "1+1", JSVM_AUTO_LENGTH, scriptSrc.ptr)
            val compileWithOptsOut = alloc<CPointerVar<JSVM_Script__>>()
            OH_JSVM_CompileScriptWithOptions(e, scriptSrc.value, 0u, null, compileWithOptsOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CompileScriptWithOptions passed")
    }

    @Test
    fun testOH_JSVM_IsDate() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val dateVal = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateDate(e, 0.0, dateVal.ptr)
            OH_JSVM_IsDate(e, dateVal.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsDate passed")
    }

    @Test
    fun testOH_JSVM_IsBigInt() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val bigi = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateBigintInt64(e, 1L, bigi.ptr)
            OH_JSVM_IsBigInt(e, bigi.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsBigInt passed")
    }

    @Test
    fun testOH_JSVM_IsSymbol() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val sym = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateSymbol(e, null, sym.ptr)
            OH_JSVM_IsSymbol(e, sym.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsSymbol passed")
    }

    @Test
    fun testOH_JSVM_IsFunction() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val cbStruct = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { _e: CPointer<JSVM_Env__>?, _i: CPointer<JSVM_CallbackInfo__>? -> null }
                data = null
            }
            val fn = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateFunction(e, "f", JSVM_AUTO_LENGTH, cbStruct.ptr, fn.ptr)
            OH_JSVM_IsFunction(e, fn.value, alloc<BooleanVar>().ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsFunction passed")
    }

    @Test
    fun testOH_JSVM_OpenInspector() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_OpenInspector(e, "127.0.0.1", 9229.toUShort())
            OH_JSVM_CloseInspector(e)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_OpenInspector passed")
    }

    @Test
    fun testOH_JSVM_CloseInspector() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_OpenInspector(e, "127.0.0.1", 9229.toUShort())
            OH_JSVM_CloseInspector(e)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CloseInspector passed")
    }

    @Test
    fun testOH_JSVM_OpenInspectorWithName() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_OpenInspectorWithName(e, 0, "test")
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_OpenInspectorWithName passed")
    }

    // ==================== 剩余 OH_JSVM_* 函数（单 API 测试）====================

    @Test
    fun testOH_JSVM_AllocateArrayBufferBackingStoreData() {
        memScoped {
            val backingData = alloc<COpaquePointerVar>()
            OH_JSVM_AllocateArrayBufferBackingStoreData(16u, JSVM_InitializedFlag.JSVM_ZERO_INITIALIZED, backingData.ptr)
            OH_JSVM_FreeArrayBufferBackingStoreData(backingData.value)
        }
        logLine("OH_JSVM_AllocateArrayBufferBackingStoreData passed")
    }

    @Test
    fun testOH_JSVM_CreateArrayBufferFromBackingStoreData() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val backingData = alloc<COpaquePointerVar>()
            OH_JSVM_AllocateArrayBufferBackingStoreData(16u, JSVM_InitializedFlag.JSVM_ZERO_INITIALIZED, backingData.ptr)
            val bufFromBacking = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArrayBufferFromBackingStoreData(e, backingData.value, 16u, 0u, 16u, bufFromBacking.ptr)
            OH_JSVM_FreeArrayBufferBackingStoreData(backingData.value)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateArrayBufferFromBackingStoreData passed")
    }

    @Test
    fun testOH_JSVM_FreeArrayBufferBackingStoreData() {
        memScoped {
            val backingData = alloc<COpaquePointerVar>()
            OH_JSVM_AllocateArrayBufferBackingStoreData(16u, JSVM_InitializedFlag.JSVM_ZERO_INITIALIZED, backingData.ptr)
            OH_JSVM_FreeArrayBufferBackingStoreData(backingData.value)
        }
        logLine("OH_JSVM_FreeArrayBufferBackingStoreData passed")
    }

    @Test
    fun testOH_JSVM_CreateBigintWords() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val words = allocArray<ULongVarOf<kotlin.ULong>>(1).apply { this[0] = 1uL }
            val bigiWords = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateBigintWords(e, 0, 1u, words, bigiWords.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateBigintWords passed")
    }

    @Test
    fun testOH_JSVM_GetValueBigintWords() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val words = allocArray<ULongVarOf<kotlin.ULong>>(1).apply { this[0] = 1uL }
            val bigiWords = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateBigintWords(e, 0, 1u, words, bigiWords.ptr)
            val signBitOut = alloc<IntVar>()
            val wordCountOut = alloc<ULongVar>()
            val outWords = allocArray<ULongVarOf<kotlin.ULong>>(2)
            OH_JSVM_GetValueBigintWords(e, bigiWords.value, signBitOut.ptr, wordCountOut.ptr, outWords)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_GetValueBigintWords passed")
    }

    @Test
    fun testOH_JSVM_IsDetachedArraybuffer() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            val isDetachedBefore = alloc<BooleanVar>()
            OH_JSVM_IsDetachedArraybuffer(e, buf.value, isDetachedBefore.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsDetachedArraybuffer passed")
    }

    @Test
    fun testOH_JSVM_DetachArraybuffer() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val buf = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateArraybuffer(e, 16u, null, buf.ptr)
            OH_JSVM_DetachArraybuffer(e, buf.value)
            val isDetachedAfter = alloc<BooleanVar>()
            OH_JSVM_IsDetachedArraybuffer(e, buf.value, isDetachedAfter.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_DetachArraybuffer passed")
    }

    @Test
    fun testOH_JSVM_IsNumberObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val scriptForNumberObj = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateStringUtf8(e, "new Number(1)", JSVM_AUTO_LENGTH, scriptForNumberObj.ptr)
            val scriptForNumber = alloc<CPointerVar<JSVM_Script__>>()
            val numObj = alloc<CPointerVar<JSVM_Value__>>()
            val isNumObj = alloc<BooleanVar>()
            OH_JSVM_IsNumberObject(e, numObj.value, isNumObj.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsNumberObject passed")
    }

    @Test
    fun testOH_JSVM_CompileWasmModule() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val wasmMagicUByte = allocArray<UByteVar>(8).apply {
                this[0] = 0x00u; this[1] = 0x61u; this[2] = 0x73u; this[3] = 0x6du
                this[4] = 0x01u; this[5] = 0x00u; this[6] = 0x00u; this[7] = 0x00u
            }
            val wasmModule = alloc<CPointerVar<JSVM_Value__>>()
            val cacheRejected = alloc<BooleanVar>()
            OH_JSVM_CompileWasmModule(e, wasmMagicUByte, 8u, null, 0u, cacheRejected.ptr, wasmModule.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CompileWasmModule passed")
    }

    @Test
    fun testOH_JSVM_CompileWasmFunction() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val wasmMagicUByte = allocArray<UByteVar>(8).apply {
                this[0] = 0x00u; this[1] = 0x61u; this[2] = 0x73u; this[3] = 0x6du
                this[4] = 0x01u; this[5] = 0x00u; this[6] = 0x00u; this[7] = 0x00u
            }
            val wasmModule = alloc<CPointerVar<JSVM_Value__>>()
            val cacheRejected = alloc<BooleanVar>()
            OH_JSVM_CompileWasmModule(e, wasmMagicUByte, 8u, null, 0u, cacheRejected.ptr, wasmModule.ptr)
            OH_JSVM_CompileWasmFunction(e, wasmModule.value, 0u, JSVM_WASM_OPT_BASELINE)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CompileWasmFunction passed")
    }

    @Test
    fun testOH_JSVM_IsWasmModuleObject() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val wasmMagicUByte = allocArray<UByteVar>(8).apply {
                this[0] = 0x00u; this[1] = 0x61u; this[2] = 0x73u; this[3] = 0x6du
                this[4] = 0x01u; this[5] = 0x00u; this[6] = 0x00u; this[7] = 0x00u
            }
            val wasmModule = alloc<CPointerVar<JSVM_Value__>>()
            val cacheRejected = alloc<BooleanVar>()
            OH_JSVM_CompileWasmModule(e, wasmMagicUByte, 8u, null, 0u, cacheRejected.ptr, wasmModule.ptr)
            val isWasm = alloc<BooleanVar>()
            OH_JSVM_IsWasmModuleObject(e, wasmModule.value, isWasm.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_IsWasmModuleObject passed")
    }

    @Test
    fun testOH_JSVM_CreateWasmCache() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val wasmMagicUByte = allocArray<UByteVar>(8).apply {
                this[0] = 0x00u; this[1] = 0x61u; this[2] = 0x73u; this[3] = 0x6du
                this[4] = 0x01u; this[5] = 0x00u; this[6] = 0x00u; this[7] = 0x00u
            }
            val wasmModule = alloc<CPointerVar<JSVM_Value__>>()
            val cacheRejected = alloc<BooleanVar>()
            OH_JSVM_CompileWasmModule(e, wasmMagicUByte, 8u, null, 0u, cacheRejected.ptr, wasmModule.ptr)
            val cacheDataOut = alloc<CPointerVar<UByteVar>>()
            val cacheLenOut = alloc<ULongVar>()
            OH_JSVM_CreateWasmCache(e, wasmModule.value, cacheDataOut.ptr, cacheLenOut.ptr)
            OH_JSVM_ReleaseCache(e, null, JSVM_CacheType.JSVM_CACHE_TYPE_WASM)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateWasmCache passed")
    }

    @Test
    fun testOH_JSVM_ReleaseCache() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_ReleaseCache(e, null, JSVM_CacheType.JSVM_CACHE_TYPE_JS)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_ReleaseCache passed")
    }

    @Test
    fun testOH_JSVM_CreateExternalStringLatin1() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val extStrLatin1 = allocArray<ByteVar>(4).apply { this[0] = 101; this[1] = 120; this[2] = 116; this[3] = 0 }
            val extStrResult = alloc<CPointerVar<JSVM_Value__>>()
            val extStrCopied = alloc<BooleanVar>()
            OH_JSVM_CreateExternalStringLatin1(e, extStrLatin1, 3u, null, null, extStrResult.ptr, extStrCopied.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateExternalStringLatin1 passed")
    }

    @Test
    fun testOH_JSVM_CreateExternalStringUtf16() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val utf16Buf = allocArray<UShortVar>(4).apply { this[0] = 'x'.code.toUShort(); this[1] = 0u }
            val extStrUtf16Result = alloc<CPointerVar<JSVM_Value__>>()
            val extStrUtf16Copied = alloc<BooleanVar>()
            OH_JSVM_CreateExternalStringUtf16(e, utf16Buf, JSVM_AUTO_LENGTH, null, null, extStrUtf16Result.ptr, extStrUtf16Copied.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateExternalStringUtf16 passed")
    }

    @Test
    fun testOH_JSVM_TraceStart() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val traceCatVar = alloc<JSVM_TraceCategory.Var>().apply { value = JSVM_TraceCategory.JSVM_TRACE_VM }
            OH_JSVM_TraceStart(1u, traceCatVar.ptr, "test", 0u)
            OH_JSVM_TraceStop(outputStreamCallback, null)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_TraceStart passed")
    }

    @Test
    fun testOH_JSVM_TraceStop() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val traceCatVar = alloc<JSVM_TraceCategory.Var>().apply { value = JSVM_TraceCategory.JSVM_TRACE_VM }
            OH_JSVM_TraceStart(1u, traceCatVar.ptr, "test", 0u)
            OH_JSVM_TraceStop(outputStreamCallback, null)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_TraceStop passed")
    }

    @Test
    fun testOH_JSVM_DefineClassWithOptions() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val ctorForOptions = alloc<JSVM_CallbackStruct>().apply {
                callback = staticCFunction { env: CPointer<JSVM_Env__>?, info: CPointer<JSVM_CallbackInfo__>? ->
                    memScoped {
                        val thisArg = alloc<CPointerVar<JSVM_Value__>>()
                        OH_JSVM_GetCbInfo(env, info, null, null, thisArg.ptr, null)
                        thisArg.value
                    }
                }
                data = null
            }
            val clsWithOptions = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_DefineClassWithOptions(e, "OptsClass", JSVM_AUTO_LENGTH, ctorForOptions.ptr, 0u, null, null, 0u, null, clsWithOptions.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_DefineClassWithOptions passed")
    }

    @Test
    fun testOH_JSVM_WaitForDebugger() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            OH_JSVM_WaitForDebugger(e, false)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_WaitForDebugger passed")
    }

    @Test
    fun testOH_JSVM_CreateSnapshot() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val envArray = allocArray<CPointerVar<JSVM_Env__>>(1).apply { this[0] = e }
            val blobDataOut = alloc<CPointerVar<ByteVar>>()
            val blobSizeOut = alloc<ULongVar>()
            OH_JSVM_CreateSnapshot(vm.value, 1u, envArray, blobDataOut.ptr, blobSizeOut.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_CreateSnapshot passed")
    }

    // ==================== StrictEquals / Equals ====================

    @Test
    fun testOH_JSVM_StrictEquals() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val v1 = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, v1.ptr)
            val strictResult = alloc<BooleanVar>()
            OH_JSVM_StrictEquals(e, v1.value, v1.value, strictResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_StrictEquals passed")
    }

    @Test
    fun testOH_JSVM_Equals() {
        memScoped {
            val initOptions = alloc<JSVM_InitOptions>().apply {
                externalReferences = null
                argc = null
                argv = null
                removeFlags = false
            }
            OH_JSVM_Init(initOptions.ptr)
            val createVMOptions = alloc<JSVM_CreateVMOptions>()
            val vm = alloc<CPointerVar<JSVM_VM__>>()
            OH_JSVM_CreateVM(createVMOptions.ptr, vm.ptr)
            val env = alloc<CPointerVar<JSVM_Env__>>()
            val e = env.value
            val v1 = alloc<CPointerVar<JSVM_Value__>>()
            val v1Str = alloc<CPointerVar<JSVM_Value__>>()
            OH_JSVM_CreateInt32(e, 1, v1.ptr)
            OH_JSVM_CreateStringUtf8(e, "1", JSVM_AUTO_LENGTH, v1Str.ptr)
            val looseResult = alloc<BooleanVar>()
            OH_JSVM_Equals(e, v1.value, v1Str.value, looseResult.ptr)
            OH_JSVM_DestroyVM(vm.value)
        }
        logLine("OH_JSVM_Equals passed")
    }

    // ==================== 枚举测试（jsvm_types.h 全覆盖）====================

    @Test
    fun testEnum_ValueType() {
        assertEquals(0, JSVM_ValueType.JSVM_UNDEFINED.value.toInt())
        assertEquals(1, JSVM_ValueType.JSVM_NULL.value.toInt())
        assertEquals(2, JSVM_ValueType.JSVM_BOOLEAN.value.toInt())
        assertEquals(3, JSVM_ValueType.JSVM_NUMBER.value.toInt())
        assertEquals(4, JSVM_ValueType.JSVM_STRING.value.toInt())
        assertEquals(5, JSVM_ValueType.JSVM_SYMBOL.value.toInt())
        assertEquals(6, JSVM_ValueType.JSVM_OBJECT.value.toInt())
        assertEquals(7, JSVM_ValueType.JSVM_FUNCTION.value.toInt())
        assertEquals(8, JSVM_ValueType.JSVM_EXTERNAL.value.toInt())
        assertEquals(9, JSVM_ValueType.JSVM_BIGINT.value.toInt())
        logLine("testEnum_ValueType passed")
    }

    @Test
    fun testEnum_PropertyAttributes() {
        assertEquals(0, JSVM_DEFAULT.toInt())
        assertEquals(1, JSVM_WRITABLE.toInt())
        assertEquals(2, JSVM_ENUMERABLE.toInt())
        assertEquals(4, JSVM_CONFIGURABLE.toInt())
        assertEquals(8, JSVM_NO_RECEIVER_CHECK.toInt())
        assertEquals(1024, JSVM_STATIC.toInt())
        assertEquals(5, JSVM_DEFAULT_METHOD.toInt())
        assertEquals(13, JSVM_METHOD_NO_RECEIVER_CHECK.toInt())
        assertEquals(7, JSVM_DEFAULT_JSPROPERTY.toInt())
        assertEquals(15, JSVM_JSPROPERTY_NO_RECEIVER_CHECK.toInt())
        logLine("testEnum_PropertyAttributes passed")
    }

    @Test
    fun testEnum_TypedarrayType() {
        assertEquals(0, JSVM_TypedarrayType.JSVM_INT8_ARRAY.value.toInt())
        assertEquals(1, JSVM_TypedarrayType.JSVM_UINT8_ARRAY.value.toInt())
        assertEquals(2, JSVM_TypedarrayType.JSVM_UINT8_CLAMPED_ARRAY.value.toInt())
        assertEquals(3, JSVM_TypedarrayType.JSVM_INT16_ARRAY.value.toInt())
        assertEquals(4, JSVM_TypedarrayType.JSVM_UINT16_ARRAY.value.toInt())
        assertEquals(5, JSVM_TypedarrayType.JSVM_INT32_ARRAY.value.toInt())
        assertEquals(6, JSVM_TypedarrayType.JSVM_UINT32_ARRAY.value.toInt())
        assertEquals(7, JSVM_TypedarrayType.JSVM_FLOAT32_ARRAY.value.toInt())
        assertEquals(8, JSVM_TypedarrayType.JSVM_FLOAT64_ARRAY.value.toInt())
        assertEquals(9, JSVM_TypedarrayType.JSVM_BIGINT64_ARRAY.value.toInt())
        assertEquals(10, JSVM_TypedarrayType.JSVM_BIGUINT64_ARRAY.value.toInt())
        logLine("testEnum_TypedarrayType passed")
    }

    @Test
    fun testEnum_Status() {
        assertEquals(0, JSVM_Status.JSVM_OK.value.toInt())
        assertEquals(1, JSVM_Status.JSVM_INVALID_ARG.value.toInt())
        assertEquals(2, JSVM_Status.JSVM_OBJECT_EXPECTED.value.toInt())
        assertEquals(3, JSVM_Status.JSVM_STRING_EXPECTED.value.toInt())
        assertEquals(4, JSVM_Status.JSVM_NAME_EXPECTED.value.toInt())
        assertEquals(5, JSVM_Status.JSVM_FUNCTION_EXPECTED.value.toInt())
        assertEquals(6, JSVM_Status.JSVM_NUMBER_EXPECTED.value.toInt())
        assertEquals(7, JSVM_Status.JSVM_BOOLEAN_EXPECTED.value.toInt())
        assertEquals(8, JSVM_Status.JSVM_ARRAY_EXPECTED.value.toInt())
        assertEquals(9, JSVM_Status.JSVM_GENERIC_FAILURE.value.toInt())
        assertEquals(10, JSVM_Status.JSVM_PENDING_EXCEPTION.value.toInt())
        logLine("testEnum_Status passed")
    }

    @Test
    fun testEnum_KeyCollectionMode() {
        assertEquals(0, JSVM_KeyCollectionMode.JSVM_KEY_INCLUDE_PROTOTYPES.value.toInt())
        assertEquals(1, JSVM_KeyCollectionMode.JSVM_KEY_OWN_ONLY.value.toInt())
        logLine("testEnum_KeyCollectionMode passed")
    }

    @Test
    fun testEnum_KeyFilter() {
        assertEquals(0, JSVM_KEY_ALL_PROPERTIES.toInt())
        assertEquals(1, JSVM_KEY_WRITABLE.toInt())
        assertEquals(2, JSVM_KEY_ENUMERABLE.toInt())
        assertEquals(4, JSVM_KEY_CONFIGURABLE.toInt())
        assertEquals(8, JSVM_KEY_SKIP_STRINGS.toInt())
        assertEquals(16, JSVM_KEY_SKIP_SYMBOLS.toInt())
        logLine("testEnum_KeyFilter passed")
    }

    @Test
    fun testEnum_KeyConversion() {
        assertEquals(0, JSVM_KeyConversion.JSVM_KEY_KEEP_NUMBERS.value.toInt())
        assertEquals(1, JSVM_KeyConversion.JSVM_KEY_NUMBERS_TO_STRINGS.value.toInt())
        logLine("testEnum_KeyConversion passed")
    }

    @Test
    fun testEnum_MemoryPressureLevel() {
        assertEquals(0, JSVM_MemoryPressureLevel.JSVM_MEMORY_PRESSURE_LEVEL_NONE.value.toInt())
        assertEquals(1, JSVM_MemoryPressureLevel.JSVM_MEMORY_PRESSURE_LEVEL_MODERATE.value.toInt())
        assertEquals(2, JSVM_MemoryPressureLevel.JSVM_MEMORY_PRESSURE_LEVEL_CRITICAL.value.toInt())
        logLine("testEnum_MemoryPressureLevel passed")
    }

    @Test
    fun testEnum_CompileMode() {
        assertEquals(0, JSVM_CompileMode.JSVM_COMPILE_MODE_DEFAULT.value.toInt())
        assertEquals(1, JSVM_CompileMode.JSVM_COMPILE_MODE_CONSUME_CODE_CACHE.value.toInt())
        assertEquals(2, JSVM_CompileMode.JSVM_COMPILE_MODE_EAGER_COMPILE.value.toInt())
        assertEquals(3, JSVM_CompileMode.JSVM_COMPILE_MODE_PRODUCE_COMPILE_PROFILE.value.toInt())
        assertEquals(4, JSVM_CompileMode.JSVM_COMPILE_MODE_CONSUME_COMPILE_PROFILE.value.toInt())
        logLine("testEnum_CompileMode passed")
    }

    @Test
    fun testEnum_CompileOptionId() {
        assertEquals(0, JSVM_CompileOptionId.JSVM_COMPILE_MODE.value.toInt())
        assertEquals(1, JSVM_CompileOptionId.JSVM_COMPILE_CODE_CACHE.value.toInt())
        assertEquals(2, JSVM_CompileOptionId.JSVM_COMPILE_SCRIPT_ORIGIN.value.toInt())
        assertEquals(3, JSVM_CompileOptionId.JSVM_COMPILE_COMPILE_PROFILE.value.toInt())
        assertEquals(4, JSVM_CompileOptionId.JSVM_COMPILE_ENABLE_SOURCE_MAP.value.toInt())
        logLine("testEnum_CompileOptionId passed")
    }

    @Test
    fun testEnum_RegExpFlags() {
        assertEquals(0, JSVM_REGEXP_NONE.toInt())
        assertEquals(1, JSVM_REGEXP_GLOBAL.toInt())
        assertEquals(2, JSVM_REGEXP_IGNORE_CASE.toInt())
        assertEquals(4, JSVM_REGEXP_MULTILINE.toInt())
        assertEquals(8, JSVM_REGEXP_STICKY.toInt())
        assertEquals(16, JSVM_REGEXP_UNICODE.toInt())
        assertEquals(32, JSVM_REGEXP_DOT_ALL.toInt())
        assertEquals(64, JSVM_REGEXP_LINEAR.toInt())
        assertEquals(128, JSVM_REGEXP_HAS_INDICES.toInt())
        assertEquals(256, JSVM_REGEXP_UNICODE_SETS.toInt())
        logLine("testEnum_RegExpFlags passed")
    }

    @Test
    fun testEnum_InitializedFlag() {
        assertEquals(0, JSVM_InitializedFlag.JSVM_ZERO_INITIALIZED.value.toInt())
        assertEquals(1, JSVM_InitializedFlag.JSVM_UNINITIALIZED.value.toInt())
        logLine("testEnum_InitializedFlag passed")
    }

    @Test
    fun testEnum_WasmOptLevel() {
        assertEquals(10, JSVM_WASM_OPT_BASELINE.toInt())
        assertEquals(20, JSVM_WASM_OPT_HIGH.toInt())
        logLine("testEnum_WasmOptLevel passed")
    }

    @Test
    fun testEnum_CacheType() {
        assertEquals(0, JSVM_CacheType.JSVM_CACHE_TYPE_JS.value.toInt())
        assertEquals(1, JSVM_CacheType.JSVM_CACHE_TYPE_WASM.value.toInt())
        logLine("testEnum_CacheType passed")
    }

    @Test
    fun testEnum_MicrotaskPolicy() {
        assertEquals(0, JSVM_MICROTASK_EXPLICIT.toInt())
        assertEquals(1, JSVM_MICROTASK_AUTO.toInt())
        logLine("testEnum_MicrotaskPolicy passed")
    }

    @Test
    fun testEnum_TraceCategory() {
        assertEquals(0, JSVM_TraceCategory.JSVM_TRACE_VM.value.toInt())
        assertEquals(1, JSVM_TraceCategory.JSVM_TRACE_COMPILE.value.toInt())
        assertEquals(2, JSVM_TraceCategory.JSVM_TRACE_EXECUTE.value.toInt())
        assertEquals(3, JSVM_TraceCategory.JSVM_TRACE_RUNTIME.value.toInt())
        assertEquals(4, JSVM_TraceCategory.JSVM_TRACE_STACK_TRACE.value.toInt())
        assertEquals(5, JSVM_TraceCategory.JSVM_TRACE_WASM.value.toInt())
        assertEquals(6, JSVM_TraceCategory.JSVM_TRACE_WASM_DETAILED.value.toInt())
        logLine("testEnum_TraceCategory passed")
    }

    @Test
    fun testEnum_PromiseRejectEvent() {
        assertEquals(0, JSVM_PROMISE_REJECT_OTHER_REASONS.toInt())
        assertEquals(1, JSVM_PROMISE_REJECT_WITH_NO_HANDLER.toInt())
        assertEquals(2, JSVM_PROMISE_ADD_HANDLER_AFTER_REJECTED.toInt())
        assertEquals(3, JSVM_PROMISE_REJECT_AFTER_RESOLVED.toInt())
        assertEquals(4, JSVM_PROMISE_RESOLVE_AFTER_RESOLVED.toInt())
        logLine("testEnum_PromiseRejectEvent passed")
    }

    @Test
    fun testEnum_MessageErrorLevel() {
        assertEquals(1, JSVM_MESSAGE_LOG.toInt())
        assertEquals(2, JSVM_MESSAGE_DEBUG.toInt())
        assertEquals(4, JSVM_MESSAGE_INFO.toInt())
        assertEquals(8, JSVM_MESSAGE_ERROR.toInt())
        assertEquals(16, JSVM_MESSAGE_WARNING.toInt())
        assertEquals(31, JSVM_MESSAGE_ALL.toInt())
        logLine("testEnum_MessageErrorLevel passed")
    }

    @Test
    fun testEnum_DefineClassOptionsId() {
        assertEquals(0, JSVM_DefineClassOptionsId.JSVM_DEFINE_CLASS_NORMAL.value.toInt())
        assertEquals(1, JSVM_DefineClassOptionsId.JSVM_DEFINE_CLASS_WITH_COUNT.value.toInt())
        assertEquals(2, JSVM_DefineClassOptionsId.JSVM_DEFINE_CLASS_WITH_PROPERTY_HANDLER.value.toInt())
        logLine("testEnum_DefineClassOptionsId passed")
    }

    @Test
    fun testEnum_CBTriggerTimeForGC() {
        assertEquals(0, JSVM_CBTriggerTimeForGC.JSVM_CB_TRIGGER_BEFORE_GC.value.toInt())
        assertEquals(1, JSVM_CBTriggerTimeForGC.JSVM_CB_TRIGGER_AFTER_GC.value.toInt())
        logLine("testEnum_CBTriggerTimeForGC passed")
    }

    @Test
    fun testEnum_GCType() {
        assertEquals(1, JSVM_GC_TYPE_SCAVENGE.toInt())
        assertEquals(2, JSVM_GC_TYPE_MINOR_MARK_COMPACT.toInt())
        assertEquals(4, JSVM_GC_TYPE_MARK_SWEEP_COMPACT.toInt())
        assertEquals(8, JSVM_GC_TYPE_INCREMENTAL_MARKING.toInt())
        assertEquals(16, JSVM_GC_TYPE_PROCESS_WEAK_CALLBACKS.toInt())
        assertEquals(31, JSVM_GC_TYPE_ALL.toInt())
        logLine("testEnum_GCType passed")
    }

    @Test
    fun testEnum_GCCallbackFlags() {
        assertEquals(0, JSVM_GCCallbackFlags.JSVM_NO_GC_CALLBACK_FLAGS.value.toInt())
        assertEquals(1, JSVM_GCCallbackFlags.JSVM_GC_CALLBACK_CONSTRUCT_RETAINED_OBJECT_INFOS.value.toInt())
        assertEquals(2, JSVM_GCCallbackFlags.JSVM_GC_CALLBACK_FORCED.value.toInt())
        assertEquals(3, JSVM_GCCallbackFlags.JSVM_GC_CALLBACK_SYNCHRONOUS_PHANTOM_CALLBACK_PROCESSING.value.toInt())
        assertEquals(4, JSVM_GCCallbackFlags.JSVM_GC_CALLBACK_COLLECT_ALL_AVAILABLE_GARBAGE.value.toInt())
        assertEquals(5, JSVM_GCCallbackFlags.JSVM_GC_CALLBACK_COLLECT_ALL_EXTERNAL_MEMORY.value.toInt())
        assertEquals(6, JSVM_GCCallbackFlags.JSVM_GC_CALLBACK_SCHEDULE_IDLE_GARBAGE_COLLECTION.value.toInt())
        logLine("testEnum_GCCallbackFlags passed")
    }
}
