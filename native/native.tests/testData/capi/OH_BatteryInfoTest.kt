import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OH_BatteryInfoTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val capacity = platform.OH_BatteryInfo.OH_BatteryInfo_GetCapacity()
            val pluggedtype = platform.OH_BatteryInfo.OH_BatteryInfo_GetPluggedType()
            val cameraInputOpen = platform.OH_Camera.OH_CameraInput_Open(null)
            val cameraInputOpenSecureCamera = platform.OH_Camera.OH_CameraInput_OpenSecureCamera(null, null)
    }
}
