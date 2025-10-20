import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OH_PrintTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val ohprintinit = platform.OH_Print.OH_Print_Init()
            val ohprintrele = platform.OH_Print.OH_Print_Release()
    }
}
