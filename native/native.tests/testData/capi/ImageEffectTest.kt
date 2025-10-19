import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ImageEffectTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val createImageEffect = platform.ImageEffect.OH_ImageEffect_Create("ImageEffect")
            val ReleaseImageEffect = platform.ImageEffect.OH_ImageEffect_Release(createImageEffect)
    }
}
