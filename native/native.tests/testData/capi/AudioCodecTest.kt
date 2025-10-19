import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AudioCodecTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val audio = platform.AudioCodec.OH_AudioCodec_CreateByName("audio/mp3")
            val desaud = platform.AudioCodec.OH_AudioCodec_Destroy(audio)
    }
}
