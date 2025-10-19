import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OH_CommonEventTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val commonEventCreateParameters = platform.OH_CommonEvent.OH_CommonEvent_CreateParameters()
            val commonEventDestroyParameters = platform.OH_CommonEvent.OH_CommonEvent_DestroyParameters(commonEventCreateParameters)
    }
}
