import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class Native_BundleTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val currnetapp = platform.Native_Bundle.OH_NativeBundle_GetCurrentApplicationInfo()
            val appid = platform.Native_Bundle.OH_NativeBundle_GetAppId()
    }
}
