import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ImageProcessingTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val denienv = platform.ImageProcessing.OH_ImageProcessing_InitializeEnvironment()
            val dendeienv = platform.ImageProcessing.OH_ImageProcessing_DeinitializeEnvironment()
    }
}
