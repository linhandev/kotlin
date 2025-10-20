import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class FFRTTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val ffrtCondInit = platform.FFRT.ffrt_cond_init(null, null)
            val ffrtCondDestroy = platform.FFRT.ffrt_cond_destroy(null)
    }
}
