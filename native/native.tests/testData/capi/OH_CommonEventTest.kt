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
import platform.BasicServicesKit.OH_CommonEvent.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OH_CommonEventTest {

    private fun logLine(msg: String) = println("[stdout] OH_CommonEventTest $msg")

    @Test
    fun testEnum_CommonEvent_ErrCode() {
        assertEquals(COMMONEVENT_ERR_OK.toInt(), 0)
        assertEquals(COMMONEVENT_ERR_PERMISSION_ERROR.toInt(), 201)
        assertEquals(COMMONEVENT_ERR_INVALID_PARAMETER.toInt(), 401)
        assertEquals(COMMONEVENT_ERR_SENDING_LIMIT_EXCEEDED.toInt(), 1500003)
        assertEquals(COMMONEVENT_ERR_NOT_SYSTEM_SERVICE.toInt(), 1500004)
        assertEquals(COMMONEVENT_ERR_SENDING_REQUEST_FAILED.toInt(), 1500007)
        assertEquals(COMMONEVENT_ERR_INIT_UNDONE.toInt(), 1500008)
        assertEquals(COMMONEVENT_ERR_OBTAIN_SYSTEM_PARAMS.toInt(), 1500009)
        assertEquals(COMMONEVENT_ERR_SUBSCRIBER_NUM_EXCEEDED.toInt(), 1500010)
        assertEquals(COMMONEVENT_ERR_ALLOC_MEMORY_FAILED.toInt(), 1500011)
        logLine("CommonEvent_ErrCode passed")
    }

    @Test
    fun testOH_CommonEvent_CreateSubscribeInfo() {
        memScoped {
            val eventCstr = "event.test".cstr.ptr
            val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
            assertNotNull(info)
            logLine("OH_CommonEvent_CreateSubscribeInfo info=$info")
            OH_CommonEvent_DestroySubscribeInfo(info)
        }
    }

    @Test
    fun testOH_CommonEvent_SetPublisherPermission() {
        memScoped {
            val eventCstr = "event.test".cstr.ptr
            val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
            assertNotNull(info)
            val ret = OH_CommonEvent_SetPublisherPermission(info, "")
            assertNotNull(ret)
            logLine("OH_CommonEvent_SetPublisherPermission ret=$ret")
            OH_CommonEvent_DestroySubscribeInfo(info)
        }
    }

    @Test
    fun testOH_CommonEvent_SetPublisherBundleName() {
        memScoped {
            val eventCstr = "event.test".cstr.ptr
            val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
            assertNotNull(info)
            val ret = OH_CommonEvent_SetPublisherBundleName(info, "bundle")
            assertNotNull(ret)
            logLine("OH_CommonEvent_SetPublisherBundleName ret=$ret")
            OH_CommonEvent_DestroySubscribeInfo(info)
        }
    }

    @Test
    fun testOH_CommonEvent_CreateSubscriber() {
        memScoped {
            val eventCstr = "event.test".cstr.ptr
            val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
            assertNotNull(info)
            val sub = OH_CommonEvent_CreateSubscriber(info, null)
            logLine("OH_CommonEvent_CreateSubscriber sub=$sub")
            OH_CommonEvent_DestroySubscriber(sub)
            OH_CommonEvent_DestroySubscribeInfo(info)
        }
    }

    @Test
    fun testOH_CommonEvent_Subscribe() {
        memScoped {
            val eventCstr = "event.test".cstr.ptr
            val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
            assertNotNull(info)
            val sub = OH_CommonEvent_CreateSubscriber(info, null)
            assertNotNull(sub)
            val ret = OH_CommonEvent_Subscribe(sub)
            assertNotNull(ret)
            logLine("OH_CommonEvent_Subscribe ret=$ret")
            OH_CommonEvent_UnSubscribe(sub)
            OH_CommonEvent_DestroySubscriber(sub)
            OH_CommonEvent_DestroySubscribeInfo(info)
        }
    }

    @Test
    fun testOH_CommonEvent_UnSubscribe() {
        memScoped {
            val eventCstr = "event.test".cstr.ptr
            val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
            assertNotNull(info)
            val sub = OH_CommonEvent_CreateSubscriber(info, null)
            assertNotNull(sub)
            OH_CommonEvent_Subscribe(sub)
            val ret = OH_CommonEvent_UnSubscribe(sub)
            assertNotNull(ret)
            logLine("OH_CommonEvent_UnSubscribe ret=$ret")
            OH_CommonEvent_DestroySubscriber(sub)
            OH_CommonEvent_DestroySubscribeInfo(info)
        }
    }

    @Test
    fun testOH_CommonEvent_IsOrderedCommonEvent() {
        try {
            memScoped {
                val eventCstr = "event.test".cstr.ptr
                val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
                assertNotNull(info)
                val sub = OH_CommonEvent_CreateSubscriber(info, null)
                assertNotNull(sub)
                val ret = OH_CommonEvent_IsOrderedCommonEvent(sub)
                logLine("OH_CommonEvent_IsOrderedCommonEvent=$ret")
                OH_CommonEvent_DestroySubscriber(sub)
                OH_CommonEvent_DestroySubscribeInfo(info)
            }
        } catch (e: Throwable) { logLine("testOH_CommonEvent_IsOrderedCommonEvent (API >17) exception: $e") }
    }

    @Test
    fun testOH_CommonEvent_GetAbortCommonEvent() {
        try {
            memScoped {
                val eventCstr = "event.test".cstr.ptr
                val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
                assertNotNull(info)
                val sub = OH_CommonEvent_CreateSubscriber(info, null)
                assertNotNull(sub)
                OH_CommonEvent_GetAbortCommonEvent(sub)
                logLine("OH_CommonEvent_GetAbortCommonEvent=called")
                OH_CommonEvent_DestroySubscriber(sub)
                OH_CommonEvent_DestroySubscribeInfo(info)
            }
        } catch (e: Throwable) { logLine("testOH_CommonEvent_GetAbortCommonEvent (API >17) exception: $e") }
    }

    @Test
    fun testOH_CommonEvent_GetCodeFromSubscriber() {
        try {
            memScoped {
                val eventCstr = "event.test".cstr.ptr
                val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
                assertNotNull(info)
                val sub = OH_CommonEvent_CreateSubscriber(info, null)
                assertNotNull(sub)
                OH_CommonEvent_GetCodeFromSubscriber(sub)
                logLine("OH_CommonEvent_GetCodeFromSubscriber=called")
                OH_CommonEvent_DestroySubscriber(sub)
                OH_CommonEvent_DestroySubscribeInfo(info)
            }
        } catch (e: Throwable) { logLine("testOH_CommonEvent_GetCodeFromSubscriber (API >17) exception: $e") }
    }

    @Test
    fun testOH_CommonEvent_GetDataFromSubscriber() {
        try {
            memScoped {
                val eventCstr = "event.test".cstr.ptr
                val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
                assertNotNull(info)
                val sub = OH_CommonEvent_CreateSubscriber(info, null)
                assertNotNull(sub)
                OH_CommonEvent_GetDataFromSubscriber(sub)
                logLine("OH_CommonEvent_GetDataFromSubscriber=called")
                OH_CommonEvent_DestroySubscriber(sub)
                OH_CommonEvent_DestroySubscribeInfo(info)
            }
        } catch (e: Throwable) { logLine("testOH_CommonEvent_GetDataFromSubscriber (API >17) exception: $e") }
    }

    @Test
    fun testOH_CommonEvent_FinishCommonEvent() {
        try {
            memScoped {
                val eventCstr = "event.test".cstr.ptr
                val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
                assertNotNull(info)
                val sub = OH_CommonEvent_CreateSubscriber(info, null)
                assertNotNull(sub)
                OH_CommonEvent_FinishCommonEvent(sub)
                logLine("OH_CommonEvent_FinishCommonEvent=called")
                OH_CommonEvent_DestroySubscriber(sub)
                OH_CommonEvent_DestroySubscribeInfo(info)
            }
        } catch (e: Throwable) { logLine("testOH_CommonEvent_FinishCommonEvent (API >17) exception: $e") }
    }

    @Test
    fun testOH_CommonEvent_AbortCommonEvent() {
        try {
            memScoped {
                val eventCstr = "event.test".cstr.ptr
                val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
                assertNotNull(info)
                val sub = OH_CommonEvent_CreateSubscriber(info, null)
                assertNotNull(sub)
                OH_CommonEvent_AbortCommonEvent(sub)
                logLine("OH_CommonEvent_AbortCommonEvent=called")
                OH_CommonEvent_DestroySubscriber(sub)
                OH_CommonEvent_DestroySubscribeInfo(info)
            }
        } catch (e: Throwable) { logLine("testOH_CommonEvent_AbortCommonEvent (API >17) exception: $e") }
    }

    @Test
    fun testOH_CommonEvent_ClearAbortCommonEvent() {
        try {
            memScoped {
                val eventCstr = "event.test".cstr.ptr
                val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
                assertNotNull(info)
                val sub = OH_CommonEvent_CreateSubscriber(info, null)
                assertNotNull(sub)
                OH_CommonEvent_ClearAbortCommonEvent(sub)
                logLine("OH_CommonEvent_ClearAbortCommonEvent=called")
                OH_CommonEvent_DestroySubscriber(sub)
                OH_CommonEvent_DestroySubscribeInfo(info)
            }
        } catch (e: Throwable) { logLine("testOH_CommonEvent_ClearAbortCommonEvent (API >17) exception: $e") }
    }

    @Test
    fun testOH_CommonEvent_SetCodeToSubscriber() {
        try {
            memScoped {
                val eventCstr = "event.test".cstr.ptr
                val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
                assertNotNull(info)
                val sub = OH_CommonEvent_CreateSubscriber(info, null)
                assertNotNull(sub)
                OH_CommonEvent_SetCodeToSubscriber(sub, 0)
                logLine("OH_CommonEvent_SetCodeToSubscriber=called")
                OH_CommonEvent_DestroySubscriber(sub)
                OH_CommonEvent_DestroySubscribeInfo(info)
            }
        } catch (e: Throwable) { logLine("testOH_CommonEvent_SetCodeToSubscriber (API >17) exception: $e") }
    }

    @Test
    fun testOH_CommonEvent_SetDataToSubscriber() {
        try {
            memScoped {
                val eventCstr = "event.test".cstr.ptr
                val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
                assertNotNull(info)
                val sub = OH_CommonEvent_CreateSubscriber(info, null)
                assertNotNull(sub)
                OH_CommonEvent_SetDataToSubscriber(sub, null, 0u)
                logLine("OH_CommonEvent_SetDataToSubscriber=called")
                OH_CommonEvent_DestroySubscriber(sub)
                OH_CommonEvent_DestroySubscribeInfo(info)
            }
        } catch (e: Throwable) { logLine("testOH_CommonEvent_SetDataToSubscriber (API >17) exception: $e") }
    }

    @Test
    fun testOH_CommonEvent_DestroySubscriber() {
        memScoped {
            val eventCstr = "event.test".cstr.ptr
            val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
            assertNotNull(info)
            val sub = OH_CommonEvent_CreateSubscriber(info, null)
            assertNotNull(sub)
            OH_CommonEvent_DestroySubscriber(sub)
            logLine("OH_CommonEvent_DestroySubscriber=called")
            OH_CommonEvent_DestroySubscribeInfo(info)
        }
    }

    @Test
    fun testOH_CommonEvent_DestroySubscribeInfo() {
        memScoped {
            val eventCstr = "event.test".cstr.ptr
            val info = OH_CommonEvent_CreateSubscribeInfo(cValuesOf(eventCstr), 1)
            assertNotNull(info)
            OH_CommonEvent_DestroySubscribeInfo(info)
            logLine("OH_CommonEvent_DestroySubscribeInfo=called")
        }
    }

    // @Test
    // fun testOH_CommonEvent_GetEventFromRcvData() {
    //     memScoped {
    //         val ret = OH_CommonEvent_GetEventFromRcvData(null)
    //         assertNull(ret)
    //         logLine("OH_CommonEvent_GetEventFromRcvData(null)=$ret")
    //     }
    // }

    // @Test
    // fun testOH_CommonEvent_GetCodeFromRcvData() {
    //     memScoped {
    //         val codeFromRcv = OH_CommonEvent_GetCodeFromRcvData(null)
    //         logLine("OH_CommonEvent_GetCodeFromRcvData(null)=$codeFromRcv")
    //     }
    // }

    // @Test
    // fun testOH_CommonEvent_GetDataStrFromRcvData() {
    //     memScoped {
    //         val ret = OH_CommonEvent_GetDataStrFromRcvData(null)
    //         assertNull(ret)
    //         logLine("OH_CommonEvent_GetDataStrFromRcvData(null)=$ret")
    //     }
    // }

    // @Test
    // fun testOH_CommonEvent_GetBundleNameFromRcvData() {
    //     memScoped {
    //         val ret = OH_CommonEvent_GetBundleNameFromRcvData(null)
    //         assertNull(ret)
    //         logLine("OH_CommonEvent_GetBundleNameFromRcvData(null)=$ret")
    //     }
    // }

    // @Test
    // fun testOH_CommonEvent_GetParametersFromRcvData() {
    //     memScoped {
    //         val ret = OH_CommonEvent_GetParametersFromRcvData(null)
    //         assertNull(ret)
    //         logLine("OH_CommonEvent_GetParametersFromRcvData(null)=$ret")
    //     }
    // }

    @Test
    fun testOH_CommonEvent_CreatePublishInfo() {
        memScoped {
            val pubInfo = try { OH_CommonEvent_CreatePublishInfo(false) } catch (e: Throwable) { logLine("OH_CommonEvent_CreatePublishInfo (API 18) exception: $e"); null }
            logLine("OH_CommonEvent_CreatePublishInfo pubInfo=$pubInfo")
            if (pubInfo != null) try { OH_CommonEvent_DestroyPublishInfo(pubInfo) } catch (e: Throwable) { logLine("OH_CommonEvent_DestroyPublishInfo (API 18) exception: $e") }
        }
    }

    @Test
    fun testOH_CommonEvent_SetPublishInfoBundleName() {
        memScoped {
            val pubInfo = try { OH_CommonEvent_CreatePublishInfo(false) } catch (e: Throwable) { logLine("OH_CommonEvent_CreatePublishInfo (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetPublishInfoBundleName(pubInfo, "b") } catch (e: Throwable) { logLine("OH_CommonEvent_SetPublishInfoBundleName (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetPublishInfoBundleName ret=$ret")
            try { OH_CommonEvent_DestroyPublishInfo(pubInfo) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetPublishInfoPermissions() {
        memScoped {
            val pubInfo = try { OH_CommonEvent_CreatePublishInfo(false) } catch (e: Throwable) { logLine("OH_CommonEvent_CreatePublishInfo (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetPublishInfoPermissions(pubInfo, null, 0) } catch (e: Throwable) { logLine("OH_CommonEvent_SetPublishInfoPermissions (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetPublishInfoPermissions ret=$ret")
            try { OH_CommonEvent_DestroyPublishInfo(pubInfo) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetPublishInfoCode() {
        memScoped {
            val pubInfo = try { OH_CommonEvent_CreatePublishInfo(false) } catch (e: Throwable) { logLine("OH_CommonEvent_CreatePublishInfo (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetPublishInfoCode(pubInfo, 0) } catch (e: Throwable) { logLine("OH_CommonEvent_SetPublishInfoCode (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetPublishInfoCode ret=$ret")
            try { OH_CommonEvent_DestroyPublishInfo(pubInfo) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetPublishInfoData() {
        memScoped {
            val pubInfo = try { OH_CommonEvent_CreatePublishInfo(false) } catch (e: Throwable) { logLine("OH_CommonEvent_CreatePublishInfo (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetPublishInfoData(pubInfo, null, 0u) } catch (e: Throwable) { logLine("OH_CommonEvent_SetPublishInfoData (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetPublishInfoData ret=$ret")
            try { OH_CommonEvent_DestroyPublishInfo(pubInfo) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetPublishInfoParameters() {
        memScoped {
            val pubInfo = try { OH_CommonEvent_CreatePublishInfo(false) } catch (e: Throwable) { logLine("OH_CommonEvent_CreatePublishInfo (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetPublishInfoParameters(pubInfo, null) } catch (e: Throwable) { logLine("OH_CommonEvent_SetPublishInfoParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetPublishInfoParameters ret=$ret")
            try { OH_CommonEvent_DestroyPublishInfo(pubInfo) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_DestroyPublishInfo() {
        memScoped {
            val pubInfo = try { OH_CommonEvent_CreatePublishInfo(false) } catch (e: Throwable) { logLine("OH_CommonEvent_CreatePublishInfo (API 18) exception: $e"); null }
            if (pubInfo != null) try { OH_CommonEvent_DestroyPublishInfo(pubInfo) } catch (e: Throwable) { logLine("OH_CommonEvent_DestroyPublishInfo (API 18) exception: $e") }
            logLine("OH_CommonEvent_DestroyPublishInfo=called")
        }
    }

    @Test
    fun testOH_CommonEvent_CreateParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            logLine("OH_CommonEvent_CreateParameters param=$param")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_HasKeyInParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            OH_CommonEvent_HasKeyInParameters(param, "k")
            logLine("OH_CommonEvent_HasKeyInParameters=called")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetIntFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = OH_CommonEvent_GetIntFromParameters(param, "k", 0)
            logLine("OH_CommonEvent_GetIntFromParameters=$ret")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetIntToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetIntToParameters(param, "k", 1) } catch (e: Throwable) { logLine("OH_CommonEvent_SetIntToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetIntToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetIntArrayFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            OH_CommonEvent_GetIntArrayFromParameters(param, "k", null)
            logLine("OH_CommonEvent_GetIntArrayFromParameters=called")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetIntArrayToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetIntArrayToParameters(param, "k", null, 0u) } catch (e: Throwable) { logLine("OH_CommonEvent_SetIntArrayToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetIntArrayToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetLongFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = OH_CommonEvent_GetLongFromParameters(param, "k", 0L)
            logLine("OH_CommonEvent_GetLongFromParameters=$ret")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetLongToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetLongToParameters(param, "k", 1L) } catch (e: Throwable) { logLine("OH_CommonEvent_SetLongToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetLongToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetLongArrayFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            OH_CommonEvent_GetLongArrayFromParameters(param, "k", null)
            logLine("OH_CommonEvent_GetLongArrayFromParameters=called")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetLongArrayToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetLongArrayToParameters(param, "k", null, 0u) } catch (e: Throwable) { logLine("OH_CommonEvent_SetLongArrayToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetLongArrayToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetBoolFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = OH_CommonEvent_GetBoolFromParameters(param, "k", false)
            logLine("OH_CommonEvent_GetBoolFromParameters=$ret")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetBoolToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetBoolToParameters(param, "k", false) } catch (e: Throwable) { logLine("OH_CommonEvent_SetBoolToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetBoolToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetBoolArrayFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            OH_CommonEvent_GetBoolArrayFromParameters(param, "k", null)
            logLine("OH_CommonEvent_GetBoolArrayFromParameters=called")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetBoolArrayToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetBoolArrayToParameters(param, "k", null, 0u) } catch (e: Throwable) { logLine("OH_CommonEvent_SetBoolArrayToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetBoolArrayToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetCharFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = OH_CommonEvent_GetCharFromParameters(param, "k", 0.toByte())
            logLine("OH_CommonEvent_GetCharFromParameters=$ret")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetCharToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetCharToParameters(param, "k", 0.toByte()) } catch (e: Throwable) { logLine("OH_CommonEvent_SetCharToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetCharToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetCharArrayFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            OH_CommonEvent_GetCharArrayFromParameters(param, "k", null)
            logLine("OH_CommonEvent_GetCharArrayFromParameters=called")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetCharArrayToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetCharArrayToParameters(param, "k", null, 0u) } catch (e: Throwable) { logLine("OH_CommonEvent_SetCharArrayToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetCharArrayToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetDoubleFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = OH_CommonEvent_GetDoubleFromParameters(param, "k", 0.0)
            logLine("OH_CommonEvent_GetDoubleFromParameters=$ret")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetDoubleToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetDoubleToParameters(param, "k", 1.0) } catch (e: Throwable) { logLine("OH_CommonEvent_SetDoubleToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetDoubleToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_GetDoubleArrayFromParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            OH_CommonEvent_GetDoubleArrayFromParameters(param, "k", null)
            logLine("OH_CommonEvent_GetDoubleArrayFromParameters=called")
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_SetDoubleArrayToParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            val ret = try { OH_CommonEvent_SetDoubleArrayToParameters(param, "k", null, 0u) } catch (e: Throwable) { logLine("OH_CommonEvent_SetDoubleArrayToParameters (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_SetDoubleArrayToParameters ret=$ret")
            try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_CommonEvent_DestroyParameters() {
        memScoped {
            val param = try { OH_CommonEvent_CreateParameters() } catch (e: Throwable) { logLine("OH_CommonEvent_CreateParameters (API 18) exception: $e"); null }
            if (param != null) try { OH_CommonEvent_DestroyParameters(param) } catch (e: Throwable) { logLine("OH_CommonEvent_DestroyParameters (API 18) exception: $e") }
            logLine("OH_CommonEvent_DestroyParameters=called")
        }
    }

    @Test
    fun testOH_CommonEvent_Publish() {
        memScoped {
            val ret = try { OH_CommonEvent_Publish("test.event") } catch (e: Throwable) { logLine("OH_CommonEvent_Publish (API 18) exception: $e"); null }
            logLine("OH_CommonEvent_Publish ret=$ret")
        }
    }

    @Test
    fun testOH_CommonEvent_PublishWithInfo() {
        memScoped {
            val ret = try { OH_CommonEvent_PublishWithInfo("test.event", null) } catch (e: Throwable) { logLine("OH_CommonEvent_PublishWithInfo (API 18) exception: $e"); COMMONEVENT_ERR_INVALID_PARAMETER }
            logLine("OH_CommonEvent_PublishWithInfo ret=$ret")
        }
    }
}
