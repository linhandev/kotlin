import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class InputMethodTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val txtavoidinfo = platform.InputMethod.OH_TextAvoidInfo_Create(2.0, 2.0)
            val txtavoidinfoset = platform.InputMethod.OH_TextAvoidInfo_SetPositionY(txtavoidinfo, 3.0)
    }
}
