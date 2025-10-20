import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVSinkBaseTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val appendOneBuffer = platform.AVSinkBase.OH_AVSamplesBuffer_AppendOneBuffer(null,null)
            val getRemainedCapacity = platform.AVSinkBase.OH_AVSamplesBuffer_GetRemainedCapacity(null)
    }
}
