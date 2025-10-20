import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class VideoProcessingTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val initEnv = platform.VideoProcessing.OH_VideoProcessing_InitializeEnvironment()
            val deinitEnv = platform.VideoProcessing.OH_VideoProcessing_DeinitializeEnvironment()
    }
}
