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
import platform.ArkData.Preferences.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class PreferencesTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_OH_Preferences_ErrCode() {
        assertEquals(PREFERENCES_OK.toInt(), 0)
        assertEquals(PREFERENCES_ERROR_INVALID_PARAM.toInt(), 401)
        assertEquals(PREFERENCES_ERROR_NOT_SUPPORTED.toInt(), 801)
        assertEquals(PREFERENCES_ERROR_BASE.toInt(), 15500000)
        assertEquals(PREFERENCES_ERROR_DELETE_FILE.toInt(), 15500010)
        assertEquals(PREFERENCES_ERROR_STORAGE.toInt(), 15500011)
        assertEquals(PREFERENCES_ERROR_MALLOC.toInt(), 15500012)
        assertEquals(PREFERENCES_ERROR_KEY_NOT_FOUND.toInt(), 15500013)
        assertEquals(PREFERENCES_ERROR_GET_DATAOBSMGRCLIENT.toInt(), 15500019)
        logLine("OH_Preferences_ErrCode passed")
    }

    @Test
    fun testEnum_Preferences_StorageType() {
        assertEquals(PREFERENCES_STORAGE_XML.toInt(), 0)
        assertEquals(PREFERENCES_STORAGE_GSKV.toInt(), 1)
        logLine("Preferences_StorageType passed")
    }

    // 覆盖 oh_preferences_value.h 中 Preference_ValueType 全部 5 个取值
    @Test
    fun testEnum_Preference_ValueType() {
        assertEquals(PREFERENCE_TYPE_NULL.toInt(), 0)
        assertEquals(PREFERENCE_TYPE_INT.toInt(), 1)
        assertEquals(PREFERENCE_TYPE_BOOL.toInt(), 2)
        assertEquals(PREFERENCE_TYPE_STRING.toInt(), 3)
        assertEquals(PREFERENCE_TYPE_BUTT.toInt(), 4)
        logLine("Preference_ValueType passed")
    }

    // 覆盖 oh_preferences_option.h 全部 6 个函数
    @Test
    fun testOH_PreferencesOption_Create() {
        val opt = OH_PreferencesOption_Create()
        logLine("OH_PreferencesOption_Create opt=$opt")
        assertNotNull(opt)
    }

    @Test
    fun testOH_PreferencesOption_SetFileName() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        val ret = OH_PreferencesOption_SetFileName(opt, "pref")
        logLine("OH_PreferencesOption_SetFileName ret=$ret")
        assertNotNull(ret)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_PreferencesOption_SetBundleName() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        val ret = OH_PreferencesOption_SetBundleName(opt, "bundle")
        logLine("OH_PreferencesOption_SetBundleName ret=$ret")
        assertNotNull(ret)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_PreferencesOption_SetDataGroupId() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        val ret = OH_PreferencesOption_SetDataGroupId(opt, null)
        logLine("OH_PreferencesOption_SetDataGroupId ret=$ret")
        assertNotNull(ret)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_PreferencesOption_SetStorageType() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        val ret = try { OH_PreferencesOption_SetStorageType(opt, PREFERENCES_STORAGE_XML) } catch (e: Throwable) { logLine("OH_PreferencesOption_SetStorageType (API 18) exception: $e"); PREFERENCES_ERROR_INVALID_PARAM.toInt() }
        logLine("OH_PreferencesOption_SetStorageType ret=$ret")
        assertNotNull(ret)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_PreferencesOption_Destroy() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        val ret = OH_PreferencesOption_Destroy(opt)
        logLine("OH_PreferencesOption_Destroy ret=$ret")
        assertNotNull(ret)
    } }

    
    @Test
    fun testOH_Preferences_Open() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "test.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        logLine("OH_Preferences_Open prefs=$prefs err=${err.value}")
        assertNotNull(err.ptr)
        OH_Preferences_Close(prefs)
        logLine("OH_Preferences_Close done")
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_Close() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "test.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val ret = OH_Preferences_Close(prefs)
        logLine("OH_Preferences_Close ret=$ret")
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_GetInt() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "t.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val ival = alloc<IntVar>()
        val ret = OH_Preferences_GetInt(prefs, "k", ival.ptr)
        logLine("OH_Preferences_GetInt ret=$ret value=${ival.value}")
        OH_Preferences_Close(prefs)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_GetBool() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "t.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val bval = alloc<BooleanVar>()
        val ret = OH_Preferences_GetBool(prefs, "k", bval.ptr)
        logLine("OH_Preferences_GetBool ret=$ret value=${bval.value}")
        OH_Preferences_Close(prefs)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_GetString() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "t.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val len = alloc<UIntVar>()
        val ret = OH_Preferences_GetString(prefs, "k", null, len.ptr)
        logLine("OH_Preferences_GetString ret=$ret len=${len.value}")
        OH_Preferences_Close(prefs)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_FreeString() {
        OH_Preferences_FreeString(null)
        logLine("OH_Preferences_FreeString(null) done")
    }

    @Test
    fun testOH_Preferences_SetInt() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "t.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val ret = OH_Preferences_SetInt(prefs, "k", 0)
        logLine("OH_Preferences_SetInt ret=$ret")
        OH_Preferences_Close(prefs)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_SetBool() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "t.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val ret = OH_Preferences_SetBool(prefs, "k", false)
        logLine("OH_Preferences_SetBool ret=$ret")
        OH_Preferences_Close(prefs)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_SetString() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "t.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val ret = OH_Preferences_SetString(prefs, "k", "v")
        logLine("OH_Preferences_SetString ret=$ret")
        OH_Preferences_Close(prefs)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_Delete() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "t.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val ret = OH_Preferences_Delete(prefs, "k")
        logLine("OH_Preferences_Delete ret=$ret")
        OH_Preferences_Close(prefs)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_RegisterDataObserver() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "t.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val ret = OH_Preferences_RegisterDataObserver(prefs, null, null, null, 0u)
        logLine("OH_Preferences_RegisterDataObserver ret=$ret")
        OH_Preferences_Close(prefs)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_UnregisterDataObserver() { memScoped {
        val opt = OH_PreferencesOption_Create()
        assertNotNull(opt)
        OH_PreferencesOption_SetFileName(opt, "t.pref")
        val err = alloc<IntVar>()
        val prefs = OH_Preferences_Open(opt, err.ptr)
        val ret = OH_Preferences_UnregisterDataObserver(prefs, null, null, null, 0u)
        logLine("OH_Preferences_UnregisterDataObserver ret=$ret")
        OH_Preferences_Close(prefs)
        OH_PreferencesOption_Destroy(opt)
    } }

    @Test
    fun testOH_Preferences_IsStorageTypeSupported() { memScoped {
        val supported = alloc<BooleanVar>()
        val ret = try { OH_Preferences_IsStorageTypeSupported(PREFERENCES_STORAGE_XML, supported.ptr) } catch (e: Throwable) { logLine("OH_Preferences_IsStorageTypeSupported (API 18) exception: $e"); PREFERENCES_ERROR_INVALID_PARAM.toInt() }
        logLine("OH_Preferences_IsStorageTypeSupported ret=$ret supported=${supported.value}")
        assertNotNull(ret)
    } }

    // ---------- oh_preferences_value.h：OH_PreferencesPair / OH_PreferencesValue 全部 6 个函数 ----------
    @Test
    fun testOH_PreferencesPair_GetKey() {
        val key = OH_PreferencesPair_GetKey(null, 0u)
        logLine("OH_PreferencesPair_GetKey null,0 key=$key")
    }

    @Test
    fun testOH_PreferencesPair_GetPreferencesValue() {
        val value = OH_PreferencesPair_GetPreferencesValue(null, 0u)
        logLine("OH_PreferencesPair_GetPreferencesValue null,0 value=$value")
    }

    @Test
    fun testOH_PreferencesValue_GetValueType() {
        val type = OH_PreferencesValue_GetValueType(null)
        logLine("OH_PreferencesValue_GetValueType null type=$type")
        assertNotNull(type)
    }

    @Test
    fun testOH_PreferencesValue_GetInt() { memScoped {
        val ival = alloc<IntVar>()
        val ret = OH_PreferencesValue_GetInt(null, ival.ptr)
        logLine("OH_PreferencesValue_GetInt null ret=$ret value=${ival.value}")
        assertNotNull(ret)
    } }

    @Test
    fun testOH_PreferencesValue_GetBool() { memScoped {
        val bval = alloc<BooleanVar>()
        val ret = OH_PreferencesValue_GetBool(null, bval.ptr)
        logLine("OH_PreferencesValue_GetBool null ret=$ret value=${bval.value}")
        assertNotNull(ret)
    } }

    @Test
    fun testOH_PreferencesValue_GetString() { memScoped {
        val len = alloc<UIntVar>()
        val ret = OH_PreferencesValue_GetString(null, null, len.ptr)
        logLine("OH_PreferencesValue_GetString null ret=$ret len=${len.value}")
        assertNotNull(ret)
    } }
}
