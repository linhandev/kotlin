import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class NativeWindowTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val nativeWindowCreate = platform.NativeWindow.OH_NativeWindow_CreateNativeWindowFromSurfaceId(1000uL, null)
            val nativeWindowDestroy = platform.NativeWindow.OH_NativeWindow_DestroyNativeWindow(null)
    }
}
