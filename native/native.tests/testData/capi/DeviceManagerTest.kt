import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class DeviceManagerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val localDeviceName = platform.DeviceManager.OH_DeviceManager_GetLocalDeviceName(null,null)
    }
}
