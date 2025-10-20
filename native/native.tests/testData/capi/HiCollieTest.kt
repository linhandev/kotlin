import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class HiCollieTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val stuckDetectionInit = platform.HiCollie.OH_HiCollie_Init_StuckDetection(null)
            val StuckDetectionWithTimeoutInit = platform.HiCollie.OH_HiCollie_Init_StuckDetectionWithTimeout(null, 1u)
    }
}
