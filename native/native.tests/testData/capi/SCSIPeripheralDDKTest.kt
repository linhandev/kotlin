import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class SCSIPeripheralDDKTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val scsiPeripheralInit = platform.SCSIPeripheralDDK.OH_ScsiPeripheral_Init()
            val scsiPeripheralRelease = platform.SCSIPeripheralDDK.OH_ScsiPeripheral_Release()
    }
}
