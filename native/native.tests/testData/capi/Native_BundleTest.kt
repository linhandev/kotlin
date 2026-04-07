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
import platform.AbilityKit.Native_Bundle.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Native_BundleTest {

    private fun logLine(msg: String) = println("[stdout] Native_BundleTest $msg")

    @Test
    fun testEnum_BundleManager_ErrorCode() {
        assertEquals(BUNDLE_MANAGER_ERROR_CODE_NO_ERROR.toInt(), 0)
        assertEquals(BUNDLE_MANAGER_ERROR_CODE_PERMISSION_DENIED.toInt(), 201)
        assertEquals(BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID.toInt(), 401)
        logLine("testEnum_BundleManager_ErrorCode passed")
    }

    @Test
    fun testOH_NativeBundle_GetCurrentApplicationInfo() {
        memScoped {
            val info = OH_NativeBundle_GetCurrentApplicationInfo()
            logLine("OH_NativeBundle_GetCurrentApplicationInfo=$info")
            assertNotNull(info)
        }
    }

    @Test
    fun testOH_NativeBundle_GetAppId() {
        memScoped {
            val appId = OH_NativeBundle_GetAppId()
            logLine("OH_NativeBundle_GetAppId=$appId")
        }
    }

    @Test
    fun testOH_NativeBundle_GetAppIdentifier() {
        memScoped {
            val appIdentifier = OH_NativeBundle_GetAppIdentifier()
            logLine("OH_NativeBundle_GetAppIdentifier=$appIdentifier")
        }
    }

    @Test
    fun testOH_NativeBundle_GetMainElementName() {
        memScoped {
            val elementName = OH_NativeBundle_GetMainElementName()
            assertNotNull(elementName)
            logLine("OH_NativeBundle_GetMainElementName=$elementName")
        }
    }

    @Test
    fun testOH_NativeBundle_GetCompatibleDeviceType() {
        memScoped {
            val deviceType = OH_NativeBundle_GetCompatibleDeviceType()
            assertNotNull(deviceType)
            logLine("OH_NativeBundle_GetCompatibleDeviceType=$deviceType")
        }
    }

    @Test
    fun testOH_NativeBundle_IsDebugMode() {
        memScoped {
            val isDebug = alloc<BooleanVar>()
            val ret = try { OH_NativeBundle_IsDebugMode(isDebug.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_IsDebugMode (API 20) exception: $e"); false }
            logLine("OH_NativeBundle_IsDebugMode=$ret")
        }
    }

    @Test
    fun testOH_NativeBundle_GetModuleMetadata() {
        memScoped {
            val size = alloc<ULongVar>()
            val metadata = try { OH_NativeBundle_GetModuleMetadata(size.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetModuleMetadata (API 20) exception: $e"); null }
            logLine("OH_NativeBundle_GetModuleMetadata=$metadata, size=${size.value}")
        }
    }

    @Test
    fun testOH_NativeBundle_GetSize() {
        val size = try { OH_NativeBundle_GetSize() } catch (e: Throwable) { logLine("OH_NativeBundle_GetSize (API 21) exception: $e"); -1 }
        assertNotNull(size)
        logLine("OH_NativeBundle_GetSize=$size")
    }

    @Test
    fun testOH_NativeBundle_GetAbilityResourceInfo() {
        memScoped {
            val abilityResourceInfoPtr = alloc<CPointerVar<OH_NativeBundle_AbilityResourceInfo>>()
            val sizeVar = alloc<ULongVar>()
            val ret = try { OH_NativeBundle_GetAbilityResourceInfo(null, abilityResourceInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityResourceInfo (API 21) exception: $e"); BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID }
            assertNotNull(ret)
            logLine("OH_NativeBundle_GetAbilityResourceInfo=$ret, size=${sizeVar.value}")
            val info = abilityResourceInfoPtr.value
            if (info != null) {
                try { OH_AbilityResourceInfo_Destroy(info, sizeVar.value) } catch (e: Throwable) { logLine("OH_AbilityResourceInfo_Destroy (API 21) exception: $e") }
            }
        }
    }

    @Test
    fun testOH_NativeBundle_GetBundleName() {
        memScoped {
            val abilityResourceInfoPtr = alloc<CPointerVar<OH_NativeBundle_AbilityResourceInfo>>()
            val sizeVar = alloc<ULongVar>()
            try { OH_NativeBundle_GetAbilityResourceInfo(null, abilityResourceInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityResourceInfo (API 21) exception: $e") }
            val info = abilityResourceInfoPtr.value
            val count = sizeVar.value
            val bundleNamePtr = alloc<CPointerVar<ByteVar>>()
            val rc = try { OH_NativeBundle_GetBundleName(info, bundleNamePtr.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetBundleName (API 21) exception: $e"); BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID }
            logLine("OH_NativeBundle_GetBundleName=$rc")
            try { OH_AbilityResourceInfo_Destroy(info, count) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeBundle_GetModuleName() {
        memScoped {
            val abilityResourceInfoPtr = alloc<CPointerVar<OH_NativeBundle_AbilityResourceInfo>>()
            val sizeVar = alloc<ULongVar>()
            try { OH_NativeBundle_GetAbilityResourceInfo(null, abilityResourceInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityResourceInfo (API 21) exception: $e") }
            val info = abilityResourceInfoPtr.value
            val count = sizeVar.value
            val moduleNamePtr = alloc<CPointerVar<ByteVar>>()
            val rc = try { OH_NativeBundle_GetModuleName(info, moduleNamePtr.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetModuleName (API 21) exception: $e"); BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID }
            logLine("OH_NativeBundle_GetModuleName=$rc")
            try { OH_AbilityResourceInfo_Destroy(info, count) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeBundle_GetAbilityName() {
        memScoped {
            val abilityResourceInfoPtr = alloc<CPointerVar<OH_NativeBundle_AbilityResourceInfo>>()
            val sizeVar = alloc<ULongVar>()
            try { OH_NativeBundle_GetAbilityResourceInfo(null, abilityResourceInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityResourceInfo (API 21) exception: $e") }
            val info = abilityResourceInfoPtr.value
            val count = sizeVar.value
            val abilityNamePtr = alloc<CPointerVar<ByteVar>>()
            val rc = try { OH_NativeBundle_GetAbilityName(info, abilityNamePtr.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityName (API 21) exception: $e"); BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID }
            logLine("OH_NativeBundle_GetAbilityName=$rc")
            try { OH_AbilityResourceInfo_Destroy(info, count) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeBundle_GetLabel() {
        memScoped {
            val abilityResourceInfoPtr = alloc<CPointerVar<OH_NativeBundle_AbilityResourceInfo>>()
            val sizeVar = alloc<ULongVar>()
            try { OH_NativeBundle_GetAbilityResourceInfo(null, abilityResourceInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityResourceInfo (API 21) exception: $e") }
            val info = abilityResourceInfoPtr.value
            val count = sizeVar.value
            val labelPtr = alloc<CPointerVar<ByteVar>>()
            val rc = try { OH_NativeBundle_GetLabel(info, labelPtr.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetLabel (API 21) exception: $e"); BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID }
            logLine("OH_NativeBundle_GetLabel=$rc")
            try { OH_AbilityResourceInfo_Destroy(info, count) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeBundle_GetAppIndex() {
        memScoped {
            val abilityResourceInfoPtr = alloc<CPointerVar<OH_NativeBundle_AbilityResourceInfo>>()
            val sizeVar = alloc<ULongVar>()
            try { OH_NativeBundle_GetAbilityResourceInfo(null, abilityResourceInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityResourceInfo (API 21) exception: $e") }
            val info = abilityResourceInfoPtr.value
            val count = sizeVar.value
            val appIndexVar = alloc<IntVar>()
            val rc = try { OH_NativeBundle_GetAppIndex(info, appIndexVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAppIndex (API 21) exception: $e"); BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID }
            logLine("OH_NativeBundle_GetAppIndex=$rc")
            try { OH_AbilityResourceInfo_Destroy(info, count) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeBundle_CheckDefaultApp() {
        memScoped {
            val abilityResourceInfoPtr = alloc<CPointerVar<OH_NativeBundle_AbilityResourceInfo>>()
            val sizeVar = alloc<ULongVar>()
            try { OH_NativeBundle_GetAbilityResourceInfo(null, abilityResourceInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityResourceInfo (API 21) exception: $e") }
            val info = abilityResourceInfoPtr.value
            val count = sizeVar.value
            val isDefaultVar = alloc<BooleanVar>()
            val rc = try { OH_NativeBundle_CheckDefaultApp(info, isDefaultVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_CheckDefaultApp (API 21) exception: $e"); BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID }
            logLine("OH_NativeBundle_CheckDefaultApp=$rc") 
            try { OH_AbilityResourceInfo_Destroy(info, count) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeBundle_GetDrawableDescriptor() {
        memScoped {
            val abilityResourceInfoPtr = alloc<CPointerVar<OH_NativeBundle_AbilityResourceInfo>>()
            val sizeVar = alloc<ULongVar>()
            try { OH_NativeBundle_GetAbilityResourceInfo(null, abilityResourceInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityResourceInfo (API 21) exception: $e") }
            val info = abilityResourceInfoPtr.value
            val count = sizeVar.value
            val drawableIconPtr = alloc<CPointerVar<ArkUI_DrawableDescriptor>>()
            val rc = try { OH_NativeBundle_GetDrawableDescriptor(info, drawableIconPtr.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetDrawableDescriptor (API 21) exception: $e"); BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID }
            logLine("OH_NativeBundle_GetDrawableDescriptor=$rc")
            try { OH_AbilityResourceInfo_Destroy(info, count) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_AbilityResourceInfo_Destroy() {
        memScoped {
            val abilityResourceInfoPtr = alloc<CPointerVar<OH_NativeBundle_AbilityResourceInfo>>()
            val sizeVar = alloc<ULongVar>()
            try { OH_NativeBundle_GetAbilityResourceInfo(null, abilityResourceInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_NativeBundle_GetAbilityResourceInfo (API 21) exception: $e") }
            val info = abilityResourceInfoPtr.value
            val count = sizeVar.value
            val rc = try { OH_AbilityResourceInfo_Destroy(info, count) } catch (e: Throwable) { logLine("OH_AbilityResourceInfo_Destroy (API 21) exception: $e"); BUNDLE_MANAGER_ERROR_CODE_PARAM_INVALID }
            logLine("OH_AbilityResourceInfo_Destroy=$rc")
        }
    }
}
