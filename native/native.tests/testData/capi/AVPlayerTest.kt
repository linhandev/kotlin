import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVPlayerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val avplayer = platform.AVPlayer.OH_AVPlayer_Create()
            val setplayerurl = platform.AVPlayer.OH_AVPlayer_SetURLSource(avplayer, "file:///path/to/media.mp4")
    }
}
