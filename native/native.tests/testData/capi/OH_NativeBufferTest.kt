import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OH_NativeBufferTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val nativeBufferAlloc = platform.OH_NativeBuffer.OH_NativeBuffer_Alloc(null)
            val nativeBufferGetSeqNum = platform.OH_NativeBuffer.OH_NativeBuffer_GetSeqNum(null)
    }
}
