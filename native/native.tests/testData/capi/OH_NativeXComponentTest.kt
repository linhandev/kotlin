import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OH_NativeXComponentTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val surfaceCallback = platform.OH_NativeXComponent.OH_ArkUI_SurfaceCallback_Create()
            val surfaceCallbackDispose = platform.OH_NativeXComponent.OH_ArkUI_SurfaceCallback_Dispose(surfaceCallback)
    }
}
