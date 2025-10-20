import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class WindowManagerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val showWindow = platform.WindowManager.OH_WindowManager_ShowWindow(0)
            val setWindowBright = platform.WindowManager.OH_WindowManager_SetWindowBrightness(0, 0.5f)
    }
}
