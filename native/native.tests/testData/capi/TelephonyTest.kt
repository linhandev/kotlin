import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class TelephonyTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val getSlotId = platform.Telephony.OH_Telephony_GetDefaultCellularDataSlotId()
    }
}
