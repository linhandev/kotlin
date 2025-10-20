import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class HitraceTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val hitraceid = platform.Hitrace.OH_HiTrace_GetChainId(null)
    }
}
