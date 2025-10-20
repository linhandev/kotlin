import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class NetConnectionTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val ohunredns = platform.NetConnection.OH_NetConn_UnregisterDnsResolver()
            val ohosunredns = platform.NetConnection.OHOS_NetConn_UnregisterDnsResolver()
    }
}
