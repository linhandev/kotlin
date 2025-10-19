import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class QoSTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val qosThreadSet = platform.QoS.OH_QoS_SetThreadQoS(111u)
            val qosThreadReset = platform.QoS.OH_QoS_ResetThreadQoS()
    }
}
