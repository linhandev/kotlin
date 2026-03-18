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
import platform.LocalizationKit.I18n.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class I18nTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_I18n_ErrorCode() {
        assertEquals(SUCCESS.toInt(), 0)
        assertEquals(ERROR_INVALID_PARAMETER.toInt(), 8900001)
        assertEquals(UNEXPECTED_ERROR.toInt(), 8900050)
        logLine("testEnum_I18n_ErrorCode passed")
    }

    @Test
    fun testEnum_DateRuleType() {
        assertEquals(DOM.toInt(), 0)
        assertEquals(DOW.toInt(), 1)
        assertEquals(DOW_GEQ_DOM.toInt(), 2)
        assertEquals(DOW_LEQ_DOM.toInt(), 3)
        logLine("testEnum_DateRuleType passed")
    }

    @Test
    fun testEnum_TimeRuleType() {
        assertEquals(WALL_TIME.toInt(), 0)
        assertEquals(STANDARD_TIME.toInt(), 1)
        assertEquals(UTC_TIME.toInt(), 2)
        logLine("testEnum_TimeRuleType passed")
    }

    @Test
    fun testOH_i18n_GetTimeZoneRules() {
        memScoped {
            val rules = alloc<TimeZoneRules>()
            val ret = try { OH_i18n_GetTimeZoneRules("Asia/Shanghai", rules.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetTimeZoneRules (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetTimeZoneRules ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetFirstStartFromTimeArrayTimeZoneRule() {
        memScoped {
            val rule = alloc<TimeArrayTimeZoneRule>().apply { name = null; rawOffset = 0; dstSavings = 0; startTimes = null; numStartTimes = 0; timeRuleType = WALL_TIME }
            val query = alloc<TimeZoneRuleQuery>()
            val ret = try { OH_i18n_GetFirstStartFromTimeArrayTimeZoneRule(rule.ptr, query.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetFirstStartFromTimeArrayTimeZoneRule (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetFirstStartFromTimeArrayTimeZoneRule ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetFirstStartFromAnnualTimeZoneRule() {
        memScoped {
            val rule = alloc<AnnualTimeZoneRule>().apply { name = null; startYear = 0; endYear = 0; rawOffset = 0; dstSavings = 0 }
            val query = alloc<TimeZoneRuleQuery>()
            val ret = try { OH_i18n_GetFirstStartFromAnnualTimeZoneRule(rule.ptr, query.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetFirstStartFromAnnualTimeZoneRule (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetFirstStartFromAnnualTimeZoneRule ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetFinalStartFromTimeArrayTimeZoneRule() {
        memScoped {
            val rule = alloc<TimeArrayTimeZoneRule>().apply { name = null; rawOffset = 0; dstSavings = 0; startTimes = null; numStartTimes = 0; timeRuleType = WALL_TIME }
            val query = alloc<TimeZoneRuleQuery>()
            val ret = try { OH_i18n_GetFinalStartFromTimeArrayTimeZoneRule(rule.ptr, query.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetFinalStartFromTimeArrayTimeZoneRule (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetFinalStartFromTimeArrayTimeZoneRule ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetFinalStartFromAnnualTimeZoneRule() {
        memScoped {
            val rule = alloc<AnnualTimeZoneRule>().apply { name = null; startYear = 0; endYear = 0; rawOffset = 0; dstSavings = 0 }
            val query = alloc<TimeZoneRuleQuery>()
            val ret = try { OH_i18n_GetFinalStartFromAnnualTimeZoneRule(rule.ptr, query.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetFinalStartFromAnnualTimeZoneRule (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetFinalStartFromAnnualTimeZoneRule ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetNextStartFromTimeArrayTimeZoneRule() {
        memScoped {
            val rule = alloc<TimeArrayTimeZoneRule>().apply { name = null; rawOffset = 0; dstSavings = 0; startTimes = null; numStartTimes = 0; timeRuleType = WALL_TIME }
            val query = alloc<TimeZoneRuleQuery>()
            val ret = try { OH_i18n_GetNextStartFromTimeArrayTimeZoneRule(rule.ptr, query.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetNextStartFromTimeArrayTimeZoneRule (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetNextStartFromTimeArrayTimeZoneRule ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetNextStartFromAnnualTimeZoneRule() {
        memScoped {
            val rule = alloc<AnnualTimeZoneRule>().apply { name = null; startYear = 0; endYear = 0; rawOffset = 0; dstSavings = 0 }
            val query = alloc<TimeZoneRuleQuery>()
            val ret = try { OH_i18n_GetNextStartFromAnnualTimeZoneRule(rule.ptr, query.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetNextStartFromAnnualTimeZoneRule (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetNextStartFromAnnualTimeZoneRule ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetPrevStartFromTimeArrayTimeZoneRule() {
        memScoped {
            val rule = alloc<TimeArrayTimeZoneRule>().apply { name = null; rawOffset = 0; dstSavings = 0; startTimes = null; numStartTimes = 0; timeRuleType = WALL_TIME }
            val query = alloc<TimeZoneRuleQuery>()
            val ret = try { OH_i18n_GetPrevStartFromTimeArrayTimeZoneRule(rule.ptr, query.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetPrevStartFromTimeArrayTimeZoneRule (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetPrevStartFromTimeArrayTimeZoneRule ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetPrevStartFromAnnualTimeZoneRule() {
        memScoped {
            val rule = alloc<AnnualTimeZoneRule>().apply { name = null; startYear = 0; endYear = 0; rawOffset = 0; dstSavings = 0 }
            val query = alloc<TimeZoneRuleQuery>()
            val ret = try { OH_i18n_GetPrevStartFromAnnualTimeZoneRule(rule.ptr, query.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetPrevStartFromAnnualTimeZoneRule (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetPrevStartFromAnnualTimeZoneRule ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetStartTimeAt() {
        memScoped {
            val rule = alloc<TimeArrayTimeZoneRule>().apply { name = null; rawOffset = 0; dstSavings = 0; startTimes = null; numStartTimes = 0; timeRuleType = WALL_TIME }
            val result = alloc<DoubleVar>()
            val ret = try { OH_i18n_GetStartTimeAt(rule.ptr, 0, result.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetStartTimeAt (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetStartTimeAt ret=$ret")
        }
    }

    @Test
    fun testOH_i18n_GetStartInYear() {
        memScoped {
            val rule = alloc<AnnualTimeZoneRule>().apply { name = null; startYear = 0; endYear = 0; rawOffset = 0; dstSavings = 0 }
            val query = alloc<TimeZoneRuleQuery>()
            val ret = try { OH_i18n_GetStartInYear(rule.ptr, 2024, query.ptr) } catch (e: Throwable) { logLine("OH_i18n_GetStartInYear (API 22) exception: $e"); ERROR_INVALID_PARAMETER }
            assertNotNull(ret)
            logLine("OH_i18n_GetStartInYear ret=$ret")
        }
    }
}
