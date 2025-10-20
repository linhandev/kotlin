import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class DeviceInfoTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val devicetype = platform.DeviceInfo.OH_GetDeviceType()
            val manufacture = platform.DeviceInfo.OH_GetManufacture()
    }
}
