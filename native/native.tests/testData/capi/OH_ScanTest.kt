import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OH_ScanTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val ohScanInit = platform.OH_Scan.OH_Scan_Init()
            val ohScanExit = platform.OH_Scan.OH_Scan_Exit()
    }
}
