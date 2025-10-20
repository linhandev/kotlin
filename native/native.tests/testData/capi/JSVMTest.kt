import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class JSVMTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val JSVMCreate = platform.JSVM.OH_JSVM_CreateVM(null, null)
            val VMScopeOpen = platform.JSVM.OH_JSVM_OpenVMScope(null, null)
    }
}
