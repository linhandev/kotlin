import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OH_DisplayManagerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val FDlistener = platform.OH_DisplayManager.OH_NativeDisplayManager_UnregisterFoldDisplayModeChangeListener(100u)
    }
}
