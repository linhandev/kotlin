import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class NativeVsyncTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val nativevsync = platform.NativeVsync.OH_NativeVSync_Create("name", 2u)
            val desnativevsync = platform.NativeVsync.OH_NativeVSync_Destroy(nativevsync)
    }
}
