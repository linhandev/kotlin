import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class BackgroundProcessManagerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val bpm = platform.BackgroundProcessManager.OH_BackgroundProcessManager_SetProcessPriority(0, 0u)
            val rbpm = platform.BackgroundProcessManager.OH_BackgroundProcessManager_ResetProcessPriority(0)
    }
}
