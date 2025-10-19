import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVRecorderTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val avrecoder = platform.AVRecorder.OH_AVRecorder_Create()
            val desavrecoder = platform.AVRecorder.OH_AVRecorder_Release(avrecoder)
    }
}
