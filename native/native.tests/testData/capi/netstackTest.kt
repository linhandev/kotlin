import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class netstackTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val httpheader = platform.netstack.OH_Http_CreateHeaders()
            val httpheaderset = platform.netstack.OH_Http_SetHeaderValue(httpheader, "key", "value")
    }
}
