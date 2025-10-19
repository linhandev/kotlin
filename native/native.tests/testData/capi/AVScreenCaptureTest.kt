import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVScreenCaptureTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val avscreencap = platform.AVScreenCapture.OH_AVScreenCapture_Create()
            val startavscreencap = platform.AVScreenCapture.OH_AVScreenCapture_StartScreenCapture(avscreencap)
    }
}
