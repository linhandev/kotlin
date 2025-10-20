import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVMuxerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val avmuxer = platform.AVMuxer.OH_AVMuxer_Create(1, 1u)
            val desavmuxer = platform.AVMuxer.OH_AVMuxer_Destroy(avmuxer)
    }
}
