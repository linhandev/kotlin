import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class MuslMallocTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val mall =  platform.MuslMalloc.malloc(100uL)
            val freemall = platform.MuslMalloc.free(mall)
    }
}
