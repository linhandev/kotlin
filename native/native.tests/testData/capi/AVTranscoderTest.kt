import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVTranscoderTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val config = platform.AVTranscoder.OH_AVTranscoderConfig_Create()
            val rele = platform.AVTranscoder.OH_AVTranscoderConfig_Release(config)
    }
}
