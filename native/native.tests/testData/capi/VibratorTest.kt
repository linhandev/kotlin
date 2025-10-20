import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class VibratorTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val vibratorCancel = platform.Vibrator.OH_Vibrator_Cancel()
    }
}
