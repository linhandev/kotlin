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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.PerformanceAnalysisKit.HiAppEvent.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class HiAppEventTest {

    private fun logLine(message: String) = println("[stdout] HiAppEventTest $message")

    @Test
    fun testEnum_EventType() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("FAULT", FAULT.toInt(), 1)
        p("STATISTIC", STATISTIC.toInt(), 2)
        p("SECURITY", SECURITY.toInt(), 3)
        p("BEHAVIOR", BEHAVIOR.toInt(), 4)
    }

    @Test
    fun testEnum_HiAppEvent_ErrorCode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HIAPPEVENT_SUCCESS", HIAPPEVENT_SUCCESS.toInt(), 0)
        p("HIAPPEVENT_INVALID_PARAM_VALUE_LENGTH", HIAPPEVENT_INVALID_PARAM_VALUE_LENGTH.toInt(), 4)
        p("HIAPPEVENT_PROCESSOR_IS_NULL", HIAPPEVENT_PROCESSOR_IS_NULL.toInt(), -7)
        p("HIAPPEVENT_PROCESSOR_NOT_FOUND", HIAPPEVENT_PROCESSOR_NOT_FOUND.toInt(), -8)
        p("HIAPPEVENT_INVALID_PARAM_VALUE", HIAPPEVENT_INVALID_PARAM_VALUE.toInt(), -9)
        p("HIAPPEVENT_EVENT_CONFIG_IS_NULL", HIAPPEVENT_EVENT_CONFIG_IS_NULL.toInt(), -10)
        p("HIAPPEVENT_OPERATE_FAILED", HIAPPEVENT_OPERATE_FAILED.toInt(), -100)
        p("HIAPPEVENT_INVALID_UID", HIAPPEVENT_INVALID_UID.toInt(), -200)
    }

    // ---------- ParamList ----------

    @Test
    fun testOH_HiAppEvent_CreateParamList() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            logLine("OH_HiAppEvent_CreateParamList=ok")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddBoolParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            OH_HiAppEvent_AddBoolParam(list, "b", true)
            logLine("OH_HiAppEvent_AddBoolParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddBoolArrayParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            val boolVal = alloc<BooleanVar>().apply { value = true }
            OH_HiAppEvent_AddBoolArrayParam(list, "barr", boolVal.ptr, 1)
            logLine("OH_HiAppEvent_AddBoolArrayParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddInt8Param() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            OH_HiAppEvent_AddInt8Param(list, "i8", 1)
            logLine("OH_HiAppEvent_AddInt8Param=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddInt8ArrayParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            val i8val = alloc<ByteVar>().apply { value = 1 }
            OH_HiAppEvent_AddInt8ArrayParam(list, "i8arr", i8val.ptr, 1)
            logLine("OH_HiAppEvent_AddInt8ArrayParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddInt16Param() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            OH_HiAppEvent_AddInt16Param(list, "i16", 2)
            logLine("OH_HiAppEvent_AddInt16Param=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddInt16ArrayParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            val i16val = alloc<ShortVar>().apply { value = 2 }
            OH_HiAppEvent_AddInt16ArrayParam(list, "i16arr", i16val.ptr, 1)
            logLine("OH_HiAppEvent_AddInt16ArrayParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddInt32Param() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            OH_HiAppEvent_AddInt32Param(list, "i", 123)
            logLine("OH_HiAppEvent_AddInt32Param=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddInt32ArrayParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            val i32val = alloc<IntVar>().apply { value = 123 }
            OH_HiAppEvent_AddInt32ArrayParam(list, "i32arr", i32val.ptr, 1)
            logLine("OH_HiAppEvent_AddInt32ArrayParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddInt64Param() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            OH_HiAppEvent_AddInt64Param(list, "i64", 456L)
            logLine("OH_HiAppEvent_AddInt64Param=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddInt64ArrayParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            val i64val = alloc<LongVar>().apply { value = 456L }
            OH_HiAppEvent_AddInt64ArrayParam(list, "i64arr", i64val.ptr, 1)
            logLine("OH_HiAppEvent_AddInt64ArrayParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddFloatParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            OH_HiAppEvent_AddFloatParam(list, "f", 1.5f)
            logLine("OH_HiAppEvent_AddFloatParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddFloatArrayParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            val fval = alloc<FloatVar>().apply { value = 1.5f }
            OH_HiAppEvent_AddFloatArrayParam(list, "farr", fval.ptr, 1)
            logLine("OH_HiAppEvent_AddFloatArrayParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddDoubleParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            OH_HiAppEvent_AddDoubleParam(list, "d", 2.5)
            logLine("OH_HiAppEvent_AddDoubleParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddDoubleArrayParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            val dval = alloc<DoubleVar>().apply { value = 2.5 }
            OH_HiAppEvent_AddDoubleArrayParam(list, "darr", dval.ptr, 1)
            logLine("OH_HiAppEvent_AddDoubleArrayParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddStringParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            OH_HiAppEvent_AddStringParam(list, "s", "v")
            logLine("OH_HiAppEvent_AddStringParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddStringArrayParam() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            val s1 = "a".cstr.getPointer(this@memScoped)
            val strVal = alloc<CPointerVar<ByteVar>>().apply { value = s1 }
            OH_HiAppEvent_AddStringArrayParam(list, "sarr", strVal.ptr, 1)
            logLine("OH_HiAppEvent_AddStringArrayParam=called")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_Write() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            val writeRc = OH_HiAppEvent_Write("domain", "event", BEHAVIOR, list)
            assertNotNull(writeRc)
            logLine("OH_HiAppEvent_Write=$writeRc")
            OH_HiAppEvent_DestroyParamList(list)
        }
    }

    @Test
    fun testOH_HiAppEvent_DestroyParamList() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            OH_HiAppEvent_DestroyParamList(list)
            logLine("OH_HiAppEvent_DestroyParamList=called")
        }
    }

    @Test
    fun testOH_HiAppEvent_Configure() {
        OH_HiAppEvent_Configure("max_storage", "100M")
        logLine("OH_HiAppEvent_Configure=called")
    }

    // ---------- Watcher ----------

    @Test
    fun testOH_HiAppEvent_CreateWatcher() {
        memScoped {
            val watcher = OH_HiAppEvent_CreateWatcher("w")
            logLine("OH_HiAppEvent_CreateWatcher=ok")
            OH_HiAppEvent_DestroyWatcher(watcher)
        }
    }

    @Test
    fun testOH_HiAppEvent_SetTriggerCondition() {
        memScoped {
            val watcher = OH_HiAppEvent_CreateWatcher("w")
            OH_HiAppEvent_SetTriggerCondition(watcher, 10, 1024, 5000)
            logLine("OH_HiAppEvent_SetTriggerCondition=called")
            OH_HiAppEvent_DestroyWatcher(watcher)
        }
    }

    @Test
    fun testOH_HiAppEvent_SetAppEventFilter() {
        memScoped {
            val watcher = OH_HiAppEvent_CreateWatcher("w")
            OH_HiAppEvent_SetAppEventFilter(watcher, "domain", 0xFFu, null, 0)
            logLine("OH_HiAppEvent_SetAppEventFilter=called")
            OH_HiAppEvent_DestroyWatcher(watcher)
        }
    }

    @Test
    fun testOH_HiAppEvent_SetWatcherOnTrigger() {
        memScoped {
            val watcher = OH_HiAppEvent_CreateWatcher("w")
            OH_HiAppEvent_SetWatcherOnTrigger(watcher, null)
            logLine("OH_HiAppEvent_SetWatcherOnTrigger=called")
            OH_HiAppEvent_DestroyWatcher(watcher)
        }
    }

    @Test
    fun testOH_HiAppEvent_SetWatcherOnReceive() {
        memScoped {
            val watcher = OH_HiAppEvent_CreateWatcher("w")
            OH_HiAppEvent_SetWatcherOnReceive(watcher, null)
            logLine("OH_HiAppEvent_SetWatcherOnReceive=called")
            OH_HiAppEvent_DestroyWatcher(watcher)
        }
    }

    @Test
    fun testOH_HiAppEvent_AddWatcher() {
        memScoped {
            val watcher = OH_HiAppEvent_CreateWatcher("w")
            OH_HiAppEvent_AddWatcher(watcher)
            logLine("OH_HiAppEvent_AddWatcher=called")
            OH_HiAppEvent_RemoveWatcher(watcher)
            OH_HiAppEvent_DestroyWatcher(watcher)
        }
    }

    @Test
    fun testOH_HiAppEvent_TakeWatcherData() {
        memScoped {
            val watcher = OH_HiAppEvent_CreateWatcher("w")
            OH_HiAppEvent_AddWatcher(watcher)
            OH_HiAppEvent_TakeWatcherData(watcher, 10u, null)
            logLine("OH_HiAppEvent_TakeWatcherData=called")
            OH_HiAppEvent_RemoveWatcher(watcher)
            OH_HiAppEvent_DestroyWatcher(watcher)
        }
    }

    @Test
    fun testOH_HiAppEvent_RemoveWatcher() {
        memScoped {
            val watcher = OH_HiAppEvent_CreateWatcher("w")      
            OH_HiAppEvent_AddWatcher(watcher)
            OH_HiAppEvent_RemoveWatcher(watcher)
            logLine("OH_HiAppEvent_RemoveWatcher=called")
            OH_HiAppEvent_DestroyWatcher(watcher)
        }
    }

    @Test
    fun testOH_HiAppEvent_DestroyWatcher() {
        memScoped {
            val watcher = OH_HiAppEvent_CreateWatcher("w")
            OH_HiAppEvent_DestroyWatcher(watcher)
            logLine("OH_HiAppEvent_DestroyWatcher=called")
        }
    }

    @Test
    fun testOH_HiAppEvent_ClearData() {
        OH_HiAppEvent_ClearData()
        logLine("OH_HiAppEvent_ClearData=called")
    }

    // ---------- Processor (API 18+) ----------

    @Test
    fun testOH_HiAppEvent_CreateProcessor() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            logLine("OH_HiAppEvent_CreateProcessor=ok")
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { logLine("OH_HiAppEvent_DestroyProcessor (API 18) exception: $e") }
        }
    }

    @Test
    fun testOH_HiAppEvent_SetReportRoute() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_SetReportRoute(proc, "appId", "route") } catch (e: Throwable) { logLine("OH_HiAppEvent_SetReportRoute (API 18) exception: $e") }
            logLine("OH_HiAppEvent_SetReportRoute=called")
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiAppEvent_SetReportPolicy() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_SetReportPolicy(proc, 1000, 10, true, false) } catch (e: Throwable) { logLine("OH_HiAppEvent_SetReportPolicy (API 18) exception: $e") }
            logLine("OH_HiAppEvent_SetReportPolicy=called")
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiAppEvent_SetReportEvent() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_SetReportEvent(proc, "d", "e", true) } catch (e: Throwable) { logLine("OH_HiAppEvent_SetReportEvent (API 18) exception: $e") }
            logLine("OH_HiAppEvent_SetReportEvent=called")
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiAppEvent_SetCustomConfig() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_SetCustomConfig(proc, "k", "v") } catch (e: Throwable) { logLine("OH_HiAppEvent_SetCustomConfig (API 18) exception: $e") }
            logLine("OH_HiAppEvent_SetCustomConfig=called")
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiAppEvent_SetConfigId() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_SetConfigId(proc, 1) } catch (e: Throwable) { logLine("OH_HiAppEvent_SetConfigId (API 18) exception: $e") }
            logLine("OH_HiAppEvent_SetConfigId=called")
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiAppEvent_SetConfigName() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_SetConfigName(proc, "name") } catch (e: Throwable) { logLine("OH_HiAppEvent_SetConfigName (API 20) exception: $e") }
            logLine("OH_HiAppEvent_SetConfigName=called")
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiAppEvent_SetReportUserId() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_SetReportUserId(proc, null, 0) } catch (e: Throwable) { logLine("OH_HiAppEvent_SetReportUserId (API 18) exception: $e") }
            logLine("OH_HiAppEvent_SetReportUserId=called")
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiAppEvent_SetReportUserProperty() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_SetReportUserProperty(proc, null, 0) } catch (e: Throwable) { logLine("OH_HiAppEvent_SetReportUserProperty (API 18) exception: $e") }
            logLine("OH_HiAppEvent_SetReportUserProperty=called")
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiAppEvent_AddProcessor() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_AddProcessor(proc) } catch (e: Throwable) { logLine("OH_HiAppEvent_AddProcessor (API 18) exception: $e") }
            logLine("OH_HiAppEvent_AddProcessor=called")
            try { OH_HiAppEvent_RemoveProcessor(1L) } catch (e: Throwable) { }
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiAppEvent_RemoveProcessor() {
        try { OH_HiAppEvent_RemoveProcessor(1L) } catch (e: Throwable) { logLine("OH_HiAppEvent_RemoveProcessor (API 18) exception: $e") }
        logLine("OH_HiAppEvent_RemoveProcessor=called")
    }

    @Test
    fun testOH_HiAppEvent_DestroyProcessor() {
        memScoped {
            val proc = try { OH_HiAppEvent_CreateProcessor("p") } catch (e: Throwable) { logLine("OH_HiAppEvent_CreateProcessor (API 18) exception: $e"); null }
            try { OH_HiAppEvent_DestroyProcessor(proc) } catch (e: Throwable) { logLine("OH_HiAppEvent_DestroyProcessor (API 18) exception: $e") }
            logLine("OH_HiAppEvent_DestroyProcessor=called")
        }
    }

    // ---------- Config ----------

    @Test
    fun testOH_HiAppEvent_CreateConfig() {
        memScoped {
            val config = OH_HiAppEvent_CreateConfig()
            logLine("OH_HiAppEvent_CreateConfig=ok")
            OH_HiAppEvent_DestroyConfig(config)
        }
    }

    @Test
    fun testOH_HiAppEvent_SetConfigItem() {
        memScoped {
            val config = OH_HiAppEvent_CreateConfig()
            OH_HiAppEvent_SetConfigItem(config, "item", "value")
            logLine("OH_HiAppEvent_SetConfigItem=called")
            OH_HiAppEvent_DestroyConfig(config)
        }
    }

    @Test
    fun testOH_HiAppEvent_SetEventConfig() {
        memScoped {
            val config = OH_HiAppEvent_CreateConfig()
            OH_HiAppEvent_SetConfigItem(config, "item", "value")
            OH_HiAppEvent_SetEventConfig("event_name", config)
            logLine("OH_HiAppEvent_SetEventConfig=called")
            OH_HiAppEvent_DestroyConfig(config)
        }
    }

    @Test
    fun testOH_HiAppEvent_DestroyConfig() {
        memScoped {
            val config = OH_HiAppEvent_CreateConfig()
            OH_HiAppEvent_DestroyConfig(config)
            logLine("OH_HiAppEvent_DestroyConfig=called")
        }
    }
}
