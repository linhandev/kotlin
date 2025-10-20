import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class TransientTaskTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cancelSuspendDelay = platform.TransientTask.OH_BackgroundTaskManager_CancelSuspendDelay(1000)
    }
}
