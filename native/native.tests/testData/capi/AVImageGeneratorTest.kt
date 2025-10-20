import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVImageGeneratorTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val avimage = platform.AVImageGenerator.OH_AVImageGenerator_Create()
            val desavimage = platform.AVImageGenerator.OH_AVImageGenerator_Release(avimage)
    }
}
