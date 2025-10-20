import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OH_CameraTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cameraInputOpen = platform.OH_Camera.OH_CameraInput_Open(null)
            val cameraInputOpenSecureCamera = platform.OH_Camera.OH_CameraInput_OpenSecureCamera(null, null)
    }
}
