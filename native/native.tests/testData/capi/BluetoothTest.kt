import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class BluetoothTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val bluetooth = platform.Bluetooth.OH_Bluetooth_GetBluetoothSwitchState(null)
    }
}
