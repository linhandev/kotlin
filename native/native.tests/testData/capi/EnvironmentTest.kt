import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class EnvironmentTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val userDownloadDir = platform.Environment.OH_Environment_GetUserDownloadDir(null)
            val userDesktopDir = platform.Environment.OH_Environment_GetUserDesktopDir(null)
    }
}
