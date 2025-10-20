import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ArkUI_NativeModuleTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val keyEventGetType = platform.ArkUI_NativeModule.OH_ArkUI_KeyEvent_GetType(null)
            val keyEventGetKeyCode = platform.ArkUI_NativeModule.OH_ArkUI_KeyEvent_GetKeyCode(null)
    }
}
