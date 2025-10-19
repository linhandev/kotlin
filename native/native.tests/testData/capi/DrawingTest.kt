import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class DrawingTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val srgb = platform.Drawing.OH_Drawing_ColorFilterCreateLinearToSrgbGamma()
            val linear = platform.Drawing.OH_Drawing_ColorFilterCreateSrgbGammaToLinear()
    }
}
