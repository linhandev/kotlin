import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OH_NativeImageTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val nativeImage =  platform.OH_NativeImage.OH_NativeImage_Create(1u, 1u)
            val nativeImageWindow =  platform.OH_NativeImage.OH_NativeImage_AcquireNativeWindow(nativeImage)
    }
}
