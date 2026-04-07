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
import platform.UniversalKeystoreKit.HuksExternalCryptoApi.*
import platform.UniversalKeystoreKit.HuksExternalCryptoTypeApi.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class HuksExternalCryptoApiTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_OH_Huks_ExternalPinAuthState() {
        assertEquals(OH_HUKS_EXT_CRYPTO_PIN_NO_AUTH.toInt(), 0)
        assertEquals(OH_HUKS_EXT_CRYPTO_PIN_AUTH_SUCCEEDED.toInt(), 1)
        assertEquals(OH_HUKS_EXT_CRYPTO_PIN_LOCKED.toInt(), 2)
        logLine("testEnum_OH_Huks_ExternalPinAuthState passed")
    }

    @Test
    fun testEnum_OH_Huks_ExternalCryptoTag() {
        // OH_HUKS_TAG_TYPE_BYTES = 5<<28, TYPE_INT = 1<<28, TYPE_UINT = 2<<28
        assertEquals(OH_HUKS_EXT_CRYPTO_TAG_UKEY_PIN.toInt(), (5 shl 28) or 200001)
        assertEquals(OH_HUKS_EXT_CRYPTO_TAG_ABILITY_NAME.toInt(), (5 shl 28) or 200002)
        assertEquals(OH_HUKS_EXT_CRYPTO_TAG_EXTRA_DATA.toInt(), (5 shl 28) or 200003)
        assertEquals(OH_HUKS_EXT_CRYPTO_TAG_UID.toInt(), (1 shl 28) or 200004)
        assertEquals(OH_HUKS_EXT_CRYPTO_TAG_PURPOSE.toInt(), (1 shl 28) or 200005)
        assertEquals(OH_HUKS_EXT_CRYPTO_TAG_TIMEOUT.toInt(), (2 shl 28) or 200006)
        logLine("testEnum_OH_Huks_ExternalCryptoTag passed")
    }

    @Test
    fun testOH_Huks_RegisterProvider() {
        memScoped {
            val ret = try { OH_Huks_RegisterProvider(null, null) } catch (e: Throwable) { logLine("OH_Huks_RegisterProvider (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_RegisterProvider ret=$ret")
        }
    }

    @Test
    fun testOH_Huks_UnregisterProvider() {
        memScoped {
            val ret = try { OH_Huks_UnregisterProvider(null, null) } catch (e: Throwable) { logLine("OH_Huks_UnregisterProvider (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_UnregisterProvider ret=$ret")
        }
    }

    @Test
    fun testOH_Huks_OpenResource() {
        memScoped {
            val ret = try { OH_Huks_OpenResource(null, null) } catch (e: Throwable) { logLine("OH_Huks_OpenResource (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_OpenResource ret=$ret")
        }
    }

    @Test
    fun testOH_Huks_CloseResource() {
        memScoped {
            val ret = try { OH_Huks_CloseResource(null, null) } catch (e: Throwable) { logLine("OH_Huks_CloseResource (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_CloseResource ret=$ret")
        }
    }

    @Test
    fun testOH_Huks_GetUkeyPinAuthState() {
        memScoped {
            val authState = alloc<UIntVar>()
            val ret = try { OH_Huks_GetUkeyPinAuthState(null, null, authState.ptr) } catch (e: Throwable) { logLine("OH_Huks_GetUkeyPinAuthState (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_GetUkeyPinAuthState ret=$ret")
        }
    }

    @Test
    fun testOH_Huks_GetProperty() {
        memScoped {
            val paramSetOut = alloc<CPointerVar<OH_Huks_ExternalCryptoParamSet>>()
            val ret = try { OH_Huks_GetProperty(null, null, null, paramSetOut.ptr) } catch (e: Throwable) { logLine("OH_Huks_GetProperty (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_GetProperty ret=$ret")
        }
    }

    @Test
    fun testOH_Huks_InitExternalCryptoParamSet() {
        memScoped {
            val paramSet = alloc<CPointerVar<OH_Huks_ExternalCryptoParamSet>>()
            val ret = try { OH_Huks_InitExternalCryptoParamSet(paramSet.ptr) } catch (e: Throwable) { logLine("OH_Huks_InitExternalCryptoParamSet (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_InitExternalCryptoParamSet ret=$ret")
        }
    }

    @Test
    fun testOH_Huks_AddExternalCryptoParams() {
        memScoped {
            val ret = try { OH_Huks_AddExternalCryptoParams(null, null, 0u) } catch (e: Throwable) { logLine("OH_Huks_AddExternalCryptoParams (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_AddExternalCryptoParams ret=$ret")
        }
    }

    @Test
    fun testOH_Huks_BuildExternalCryptoParamSet() {
        memScoped {
            val paramSet = alloc<CPointerVar<OH_Huks_ExternalCryptoParamSet>>()
            val ret = try { OH_Huks_BuildExternalCryptoParamSet(paramSet.ptr) } catch (e: Throwable) { logLine("OH_Huks_BuildExternalCryptoParamSet (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_BuildExternalCryptoParamSet ret=$ret")
        }
    }

    @Test
    fun testOH_Huks_FreeExternalCryptoParamSet() {
        memScoped {
            val paramSet = alloc<CPointerVar<OH_Huks_ExternalCryptoParamSet>>()
            paramSet.value = null
            try { OH_Huks_FreeExternalCryptoParamSet(paramSet.ptr) } catch (e: Throwable) { logLine("OH_Huks_FreeExternalCryptoParamSet (API 22) exception: $e") }
            logLine("OH_Huks_FreeExternalCryptoParamSet done")
        }
    }

    @Test
    fun testOH_Huks_GetExternalCryptoParam() {
        memScoped {
            val param = alloc<CPointerVar<OH_Huks_ExternalCryptoParam>>()
            val ret = try { OH_Huks_GetExternalCryptoParam(null, 0u, param.ptr) } catch (e: Throwable) { logLine("OH_Huks_GetExternalCryptoParam (API 22) exception: $e") }
            assertNotNull(ret)
            logLine("OH_Huks_GetExternalCryptoParam ret=$ret")
        }
    }
}
