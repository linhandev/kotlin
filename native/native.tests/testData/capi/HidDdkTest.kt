import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class HidDdkTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val hidDeviceCreate = platform.HidDdk.OH_Hid_CreateDevice(null, null)
            val hidEventEmit = platform.HidDdk.OH_Hid_EmitEvent(1,null, 2u)
    }
}
