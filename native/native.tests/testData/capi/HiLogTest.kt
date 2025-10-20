import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class HiLogTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val hilogmsg =  platform.HiLog.OH_LOG_PrintMsg(0u, 3u, 1u, "tag", "Hello from Kotlin/Native!")
            val hilogmsglen =  platform.HiLog.OH_LOG_PrintMsgByLen(0u, 4u, 2u, "tag", 10uL, "Hello from Kotlin/Native!", 20uL)
    }
}
