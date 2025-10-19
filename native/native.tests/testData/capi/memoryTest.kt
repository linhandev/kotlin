import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class memoryTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val memoryCreate = platform.memory.OH_PurgeableMemory_Create(1uL, null, null)
            val memoryEndRead = platform.memory.OH_PurgeableMemory_EndRead(memoryCreate)
    }
}
