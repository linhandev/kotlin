import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class TeeClientTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val teecInit = platform.TeeClient.TEEC_InitializeContext("teec", null)
            val teecFin = platform.TeeClient.TEEC_FinalizeContext(null)
    }
}
