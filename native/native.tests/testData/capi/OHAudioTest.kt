import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OHAudioTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val AudioSessionActivate = platform.OHAudio.OH_AudioSessionManager_ActivateAudioSession(null, null)
            val AudioSessionDeactivate = platform.OHAudio.OH_AudioSessionManager_DeactivateAudioSession(null)
    }
}
