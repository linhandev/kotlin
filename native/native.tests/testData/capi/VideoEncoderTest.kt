import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class VideoEncoderTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val videoEncoderCreate = platform.VideoEncoder.OH_VideoEncoder_CreateByName("videoEnc")
            val videoEncoderDestroy = platform.VideoEncoder.OH_VideoEncoder_Destroy(videoEncoderCreate)
    }
}
