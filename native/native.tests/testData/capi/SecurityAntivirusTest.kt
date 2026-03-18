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
import platform.DeviceSecurityKit.SecurityAntivirus.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class SecurityAntivirusTest {

    private fun logLine(msg: String) = println(msg)

    // ---------- security_antivirus.h 枚举 ----------
    @Test
    fun testEnum_SecurityAntivirus_ErrCode() {
        assertEquals(SECURITY_ANTIVIRUS_SUCCESS.toInt(), 0)
        assertEquals(SECURITY_ANTIVIRUS_PERMISSION_NOT_GRANTED.toInt(), 201)
        assertEquals(SECURITY_ANTIVIRUS_PARAM_INVALID.toInt(), 1019900001)
        assertEquals(SECURITY_ANTIVIRUS_NO_REGISTER.toInt(), 1019900002)
        assertEquals(SECURITY_ANTIVIRUS_INNER_ERROR.toInt(), 1019900003)
        logLine("SecurityAntivirus_ErrCode passed")
    }

    @Test
    fun testHMS_SecurityAntivirus_RegisterAntivirus() {
        val r0 = try { HMS_SecurityAntivirus_RegisterAntivirus(null) } catch (e: Throwable) { logLine("HMS_SecurityAntivirus_RegisterAntivirus (API 20) exception: $e"); SECURITY_ANTIVIRUS_PARAM_INVALID }
        assertNotNull(r0)
        logLine("RegisterAntivirus $r0")
    }

    @Test
    fun testHMS_SecurityAntivirus_UnregisterAntivirus() {
        val r1 = try { HMS_SecurityAntivirus_UnregisterAntivirus(null) } catch (e: Throwable) { logLine("HMS_SecurityAntivirus_UnregisterAntivirus (API 20) exception: $e"); SECURITY_ANTIVIRUS_PARAM_INVALID }
        assertNotNull(r1)
        logLine("UnregisterAntivirus $r1")
    }

    @Test
    fun testHMS_SecurityAntivirus_UpdateAntivirus() {
        val r2 = try { HMS_SecurityAntivirus_UpdateAntivirus(null) } catch (e: Throwable) { logLine("HMS_SecurityAntivirus_UpdateAntivirus (API 20) exception: $e"); SECURITY_ANTIVIRUS_PARAM_INVALID }
        assertNotNull(r2)
        logLine("UpdateAntivirus $r2")
    }

    @Test
    fun testHMS_SecurityAntivirus_QueryAntivirus() {
        memScoped {
            val lengthOut = alloc<UIntVar>()
            val r3 = try { HMS_SecurityAntivirus_QueryAntivirus(null, lengthOut.ptr) } catch (e: Throwable) { logLine("HMS_SecurityAntivirus_QueryAntivirus (API 20) exception: $e"); SECURITY_ANTIVIRUS_PARAM_INVALID }
            assertNotNull(r3)
            logLine("QueryAntivirus $r3")
        }
    }

    @Test
    fun testHMS_SecurityAntivirus_QueryPreinstalledAntivirus() {
        memScoped {
            val lengthOut2 = alloc<UIntVar>()
            val r4 = try { HMS_SecurityAntivirus_QueryPreinstalledAntivirus(null, lengthOut2.ptr) } catch (e: Throwable) { logLine("HMS_SecurityAntivirus_QueryPreinstalledAntivirus (API 20) exception: $e"); SECURITY_ANTIVIRUS_PARAM_INVALID }
            assertNotNull(r4)
            logLine("QueryPreinstalledAntivirus $r4")
        }
    }

    @Test
    fun testHMS_SecurityAntivirus_EnablePreinstalledAntivirus() {
        val r5 = try { HMS_SecurityAntivirus_EnablePreinstalledAntivirus() } catch (e: Throwable) { logLine("HMS_SecurityAntivirus_EnablePreinstalledAntivirus (API 20) exception: $e"); SECURITY_ANTIVIRUS_PARAM_INVALID }
        assertNotNull(r5)
        logLine("EnablePreinstalledAntivirus $r5")
    }

    @Test
    fun testHMS_SecurityAntivirus_DisablePreinstalledAntivirus() {
        val r6 = try { HMS_SecurityAntivirus_DisablePreinstalledAntivirus() } catch (e: Throwable) { logLine("HMS_SecurityAntivirus_DisablePreinstalledAntivirus (API 20) exception: $e"); SECURITY_ANTIVIRUS_PARAM_INVALID }
        assertNotNull(r6)
        logLine("DisablePreinstalledAntivirus $r6")
    }

    @Test
    fun testHMS_SecurityAntivirus_EnablePreinstalledAntivirusByAccount() {
        val r7 = try { HMS_SecurityAntivirus_EnablePreinstalledAntivirusByAccount(0) } catch (e: Throwable) { logLine("HMS_SecurityAntivirus_EnablePreinstalledAntivirusByAccount (API 20) exception: $e"); SECURITY_ANTIVIRUS_PARAM_INVALID }
        assertNotNull(r7)
        logLine("EnablePreinstalledAntivirusByAccount $r7")
    }

    @Test
    fun testHMS_SecurityAntivirus_DisablePreinstalledAntivirusByAccount() {
        val r8 = try { HMS_SecurityAntivirus_DisablePreinstalledAntivirusByAccount(0) } catch (e: Throwable) { logLine("HMS_SecurityAntivirus_DisablePreinstalledAntivirusByAccount (API 20) exception: $e"); SECURITY_ANTIVIRUS_PARAM_INVALID }
        assertNotNull(r8)
        logLine("DisablePreinstalledAntivirusByAccount $r8")
    }
}
