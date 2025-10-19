import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class HiDebugTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val syscpuusage = platform.HiDebug.OH_HiDebug_GetSystemCpuUsage()
            val appcpuusage = platform.HiDebug.OH_HiDebug_GetAppCpuUsage()
    }
}
