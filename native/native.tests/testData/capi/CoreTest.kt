import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CoreTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val avformat = platform.Core.OH_AVFormat_Create()
            val desavformat = platform.Core.OH_AVFormat_Destroy(avformat)
    }
}
