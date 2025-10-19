import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class SerialDDKTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val usbSerialInit = platform.SerialDDK.OH_UsbSerial_Init()
            val usbSerialRelease = platform.SerialDDK.OH_UsbSerial_Release()
    }
}
