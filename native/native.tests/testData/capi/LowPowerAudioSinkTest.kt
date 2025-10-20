import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class LowPowerAudioSinkTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val audiosink = platform.LowPowerAudioSink.OH_LowPowerAudioSink_CreateByMime("OH_AVCODEC_MIMETYPE_AUDIO_AAC")
            val desaudiosink = platform.LowPowerAudioSink.OH_LowPowerAudioSink_Destroy(audiosink)
    }
}
