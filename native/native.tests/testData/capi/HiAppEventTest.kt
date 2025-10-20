import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class HiAppEventTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val paramlist = platform.HiAppEvent.OH_HiAppEvent_CreateParamList()
            val desparamlist = platform.HiAppEvent.OH_HiAppEvent_DestroyParamList(paramlist)
    }
}
