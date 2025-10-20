import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class LowPowerVideoSinkTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val videosink = platform.LowPowerVideoSink.OH_LowPowerVideoSink_CreateByMime("OH_MD_KEY_VIDEO_ENCODER_QP_MAX")
            val desvideosink = platform.LowPowerVideoSink.OH_LowPowerVideoSink_Destroy(videosink)
    }
}
