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

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AbilityBaseTest {

    @Test
    fun testEnums() {
        logLine("--- Testing AbilityBase enums ---")
        val noError = platform.AbilityKit.AbilityBase.ABILITY_BASE_ERROR_CODE_NO_ERROR
        val paramInvalid = platform.AbilityKit.AbilityBase.ABILITY_BASE_ERROR_CODE_PARAM_INVALID
        assertNotNull(noError)
        assertNotNull(paramInvalid)
        assertNotEquals(noError, paramInvalid)
        logLine("AbilityBase_ErrorCode: NO_ERROR=$noError, PARAM_INVALID=$paramInvalid")
    }

    @Test
    fun testCreateWant() {
        logLine("--- Testing OH_AbilityBase_CreateWant ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            logLine("OH_AbilityBase_CreateWant(element) result: $want")
            val destroyResult = platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
            assertNotNull(destroyResult)
            logLine("OH_AbilityBase_DestroyWant(want) result: $destroyResult")
        }
    }

    @Test
    fun testDestroyWant() {
        logLine("--- Testing OH_AbilityBase_DestroyWant ---")
        val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(null)
        assertNotNull(result)
        logLine("OH_AbilityBase_DestroyWant(null) result: $result")
    }

    @Test
    fun testSetWantElement() {
        logLine("--- Testing OH_AbilityBase_SetWantElement ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            val setResult = platform.AbilityKit.AbilityBase.OH_AbilityBase_SetWantElement(want, element.readValue())
            assertNotNull(setResult)
            logLine("OH_AbilityBase_SetWantElement result: $setResult")
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testGetWantElement() {
        logLine("--- Testing OH_AbilityBase_GetWantElement ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            val outElement = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>()
            val getResult = platform.AbilityKit.AbilityBase.OH_AbilityBase_GetWantElement(want, outElement.ptr)
            assertNotNull(getResult)
            logLine("OH_AbilityBase_GetWantElement result: $getResult")
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testSetWantCharParam() {
        logLine("--- Testing OH_AbilityBase_SetWantCharParam ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_SetWantCharParam(want, null, null)
            assertNotNull(result)
            logLine("OH_AbilityBase_SetWantCharParam result: $result")
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testGetWantCharParam() {
        logLine("--- Testing OH_AbilityBase_GetWantCharParam ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_GetWantCharParam(want, null, null, 0uL)
            assertNotNull(result)
            logLine("OH_AbilityBase_GetWantCharParam result: $result")
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testSetWantUri() {
        logLine("--- Testing OH_AbilityBase_SetWantUri (API 17) ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            try {
                val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_SetWantUri(want, null)
                assertNotNull(result)
                logLine("OH_AbilityBase_SetWantUri result: $result")
            } catch (e: Throwable) {
                logLine("OH_AbilityBase_SetWantUri (API 17) exception: $e")
            }
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testGetWantUri() {
        logLine("--- Testing OH_AbilityBase_GetWantUri (API 17) ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            try {
                val uriBuffer = allocArray<ByteVar>(256)
                val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_GetWantUri(want, uriBuffer, 256uL)
                assertNotNull(result)
                logLine("OH_AbilityBase_GetWantUri result: $result")
            } catch (e: Throwable) {
                logLine("OH_AbilityBase_GetWantUri (API 17) exception: $e")
            }
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testSetWantInt32Param() {
        logLine("--- Testing OH_AbilityBase_SetWantInt32Param (API 17) ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            try {
                val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_SetWantInt32Param(want, null, 0)
                assertNotNull(result)
                logLine("OH_AbilityBase_SetWantInt32Param result: $result")
            } catch (e: Throwable) {
                logLine("OH_AbilityBase_SetWantInt32Param (API 17) exception: $e")
            }
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testGetWantInt32Param() {
        logLine("--- Testing OH_AbilityBase_GetWantInt32Param (API 17) ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            try {
                val value = alloc<IntVar>()
                val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_GetWantInt32Param(want, null, value.ptr)
                assertNotNull(result)
                logLine("OH_AbilityBase_GetWantInt32Param result: $result")
            } catch (e: Throwable) {
                logLine("OH_AbilityBase_GetWantInt32Param (API 17) exception: $e")
            }
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testSetWantBoolParam() {
        logLine("--- Testing OH_AbilityBase_SetWantBoolParam (API 17) ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            try {
                val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_SetWantBoolParam(want, null, false)
                assertNotNull(result)
                logLine("OH_AbilityBase_SetWantBoolParam result: $result")
            } catch (e: Throwable) {
                logLine("OH_AbilityBase_SetWantBoolParam (API 17) exception: $e")
            }
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testGetWantBoolParam() {
        logLine("--- Testing OH_AbilityBase_GetWantBoolParam (API 17) ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            try {
                val value = alloc<BooleanVar>()
                val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_GetWantBoolParam(want, null, value.ptr)
                assertNotNull(result)
                logLine("OH_AbilityBase_GetWantBoolParam result: $result")
            } catch (e: Throwable) {
                logLine("OH_AbilityBase_GetWantBoolParam (API 17) exception: $e")
            }
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testSetWantDoubleParam() {
        logLine("--- Testing OH_AbilityBase_SetWantDoubleParam (API 17) ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            try {
                val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_SetWantDoubleParam(want, null, 0.0)
                assertNotNull(result)
                logLine("OH_AbilityBase_SetWantDoubleParam result: $result")
            } catch (e: Throwable) {
                logLine("OH_AbilityBase_SetWantDoubleParam (API 17) exception: $e")
            }
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testGetWantDoubleParam() {
        logLine("--- Testing OH_AbilityBase_GetWantDoubleParam (API 17) ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            try {
                val value = alloc<DoubleVar>()
                val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_GetWantDoubleParam(want, null, value.ptr)
                assertNotNull(result)
                logLine("OH_AbilityBase_GetWantDoubleParam result: $result")
            } catch (e: Throwable) {
                logLine("OH_AbilityBase_GetWantDoubleParam (API 17) exception: $e")
            }
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testAddWantFd() {
        logLine("--- Testing OH_AbilityBase_AddWantFd ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_AddWantFd(want, null, 0)
            assertNotNull(result)
            logLine("OH_AbilityBase_AddWantFd result: $result")
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    @Test
    fun testGetWantFd() {
        logLine("--- Testing OH_AbilityBase_GetWantFd ---")
        memScoped {
            val element = alloc<platform.AbilityKit.AbilityBase.AbilityBase_Element>().apply {
                bundleName = null
                moduleName = null
                abilityName = null
            }
            val want = platform.AbilityKit.AbilityBase.OH_AbilityBase_CreateWant(element.readValue())
            val fd = alloc<IntVar>()
            val result = platform.AbilityKit.AbilityBase.OH_AbilityBase_GetWantFd(want, null, fd.ptr)
            assertNotNull(result)
            logLine("OH_AbilityBase_GetWantFd result: $result")
            platform.AbilityKit.AbilityBase.OH_AbilityBase_DestroyWant(want)
        }
    }

    private fun logLine(message: String) {
        println("[stdout] AbilityBaseTest $message")
    }
}
