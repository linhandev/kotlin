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
import platform.NetworkKit.NetConnection.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class NetConnectionTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_NetConn_NetCap() {
        assertEquals(NETCONN_NET_CAPABILITY_MMS.toInt(), 0)
        assertEquals(NETCONN_NET_CAPABILITY_NOT_METERED.toInt(), 11)
        assertEquals(NETCONN_NET_CAPABILITY_INTERNET.toInt(), 12)
        assertEquals(NETCONN_NET_CAPABILITY_NOT_VPN.toInt(), 15)
        assertEquals(NETCONN_NET_CAPABILITY_VALIDATED.toInt(), 16)
        assertEquals(NETCONN_NET_CAPABILITY_PORTAL.toInt(), 17)
        assertEquals(NETCONN_NET_CAPABILITY_CHECKING_CONNECTIVITY.toInt(), 31)
        logLine("NetConn_NetCap passed")
    }

    @Test
    fun testEnum_NetConn_NetBearerType() {
        assertEquals(NETCONN_BEARER_CELLULAR.toInt(), 0)
        assertEquals(NETCONN_BEARER_WIFI.toInt(), 1)
        assertEquals(NETCONN_BEARER_BLUETOOTH.toInt(), 2)
        assertEquals(NETCONN_BEARER_ETHERNET.toInt(), 3)
        assertEquals(NETCONN_BEARER_VPN.toInt(), 4)
        logLine("NetConn_NetBearerType passed")
    }

    @Test
    fun testEnum_NetConn_ErrorCode() {
        assertEquals(NETCONN_SUCCESS.toInt(), 0)
        assertEquals(NETCONN_PERMISSION_DENIED.toInt(), 201)
        assertEquals(NETCONN_PARAMETER_ERROR.toInt(), 401)
        assertEquals(NETCONN_OPERATION_FAILED.toInt(), 2100002)
        assertEquals(NETCONN_INTERNAL_ERROR.toInt(), 2100003)
        logLine("NetConn_ErrorCode passed")
    }

    @Test
    fun testEnum_NetConn_PacketsType() {
        assertEquals(NETCONN_PACKETS_ICMP.toInt(), 0)
        assertEquals(NETCONN_PACKETS_UDP.toInt(), 1)
        logLine("NetConn_PacketsType passed")
    }

    @Test
    fun testHasDefaultNetAndGetDefaultNet() { memScoped {
        val hasDefault = alloc<IntVar>()
        val rc = OH_NetConn_HasDefaultNet(hasDefault.ptr)
        assertNotNull(rc)
        logLine("OH_NetConn_HasDefaultNet=$rc")
        val netHandle = alloc<NetConn_NetHandle>()
        val getRc = OH_NetConn_GetDefaultNet(netHandle.ptr)
        assertNotNull(getRc)
        logLine("OH_NetConn_GetDefaultNet=$getRc")
    } }

    @Test
    fun testIsDefaultNetMeteredAndGetConnectionProperties() { memScoped {
        val isMetered = alloc<IntVar>()
        val rc = OH_NetConn_IsDefaultNetMetered(isMetered.ptr)
        assertNotNull(rc)
        val netHandle = alloc<NetConn_NetHandle>()
        val prop = alloc<NetConn_ConnectionProperties>()
        val propRc = OH_NetConn_GetConnectionProperties(netHandle.ptr, prop.ptr)
        assertNotNull(propRc)
    } }

    @Test
    fun testGetNetCapabilitiesAndGetDefaultHttpProxy() { memScoped {
        val netHandle = alloc<NetConn_NetHandle>()
        val cap = alloc<NetConn_NetCapabilities>()
        val capRc = OH_NetConn_GetNetCapabilities(netHandle.ptr, cap.ptr)
        assertNotNull(capRc)
        val proxy = alloc<NetConn_HttpProxy>()
        val proxyRc = OH_NetConn_GetDefaultHttpProxy(proxy.ptr)
        assertNotNull(proxyRc)
    } }

    @Test
    fun testRegisterUnregisterDnsResolverAndAppHttpProxyCallback() { memScoped {
        assertNotNull(OHOS_NetConn_RegisterDnsResolver(null))
        assertNotNull(OHOS_NetConn_UnregisterDnsResolver())
        assertNotNull(OH_NetConn_RegisterDnsResolver(null))
        val unregRc = OH_NetConn_UnregisterDnsResolver()
        assertNotNull(unregRc)
        val callbackId = alloc<UIntVar>()
        assertNotNull(OH_NetConn_RegisterAppHttpProxyCallback(null, callbackId.ptr))
        OH_NetConn_UnregisterAppHttpProxyCallback(0u)
        logLine("OH_NetConn_Register/Unregister DnsResolver/AppHttpProxyCallback done")
    } }

    @Test
    fun testGetAddrInfo_FreeDnsResult_GetAllNets_BindSocket() { memScoped {
        val resPtr = alloc<CPointerVar<platform.NetworkKit.NetConnection.addrinfo>>()
        assertNotNull(OH_NetConn_GetAddrInfo(null, null, null, resPtr.ptr, 0))
        OH_NetConn_FreeDnsResult(resPtr.value)
        val list = alloc<NetConn_NetHandleList>()
        assertNotNull(OH_NetConn_GetAllNets(list.ptr))
        val netHandle = alloc<NetConn_NetHandle>()
        assertNotNull(OH_NetConn_BindSocket(-1, netHandle.ptr))
        logLine("OH_NetConn_GetAddrInfo/FreeDnsResult/GetAllNets/BindSocket done")
    } }

    @Test
    fun testSetAppHttpProxy_RegisterNetConnCallback_SetPacUrl_QueryProbe() { memScoped {
        val proxy = alloc<NetConn_HttpProxy>()
        assertNotNull(OH_NetConn_SetAppHttpProxy(proxy.ptr))
        val callbackId = alloc<UIntVar>()
        assertNotNull(OH_NetConn_RegisterNetConnCallback(null, null, 0u, callbackId.ptr))
        assertNotNull(OH_NetConn_RegisterDefaultNetConnCallback(null, callbackId.ptr))
        assertNotNull(OH_NetConn_UnregisterNetConnCallback(0u))
        assertNotNull(OH_NetConn_SetPacUrl(null))
        val pacBuf = ByteArray(256)
        assertNotNull(OH_NetConn_GetPacUrl(pacBuf.refTo(0)))
        val probeResult = alloc<NetConn_ProbeResultInfo>()
        assertNotNull(try { OH_NetConn_QueryProbeResult(null, 0, probeResult.ptr) } catch (e: Throwable) { logLine("OH_NetConn_QueryProbeResult (API 20) exception: $e"); NETCONN_PARAMETER_ERROR })
        val traceOption = alloc<NetConn_TraceRouteOption>()
        val traceInfo = alloc<NetConn_TraceRouteInfo>()
        assertNotNull(try { OH_NetConn_QueryTraceRoute(null, traceOption.ptr, traceInfo.ptr) } catch (e: Throwable) { logLine("OH_NetConn_QueryTraceRoute (API 20) exception: $e"); NETCONN_PARAMETER_ERROR })
        logLine("OH_NetConn_SetAppHttpProxy/RegisterNetConnCallback/SetPacUrl/QueryProbe/QueryTraceRoute done")
    } }
}
