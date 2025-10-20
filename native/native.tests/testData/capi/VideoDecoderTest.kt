import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class VideoDecoderTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val videoDecoderCreate = platform.VideoDecoder.OH_VideoDecoder_CreateByName("videoDec")
            val videoDecoderDestroy = platform.VideoDecoder.OH_VideoDecoder_Destroy(videoDecoderCreate)
    }
}
