import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class inputTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val keystate = platform.input.OH_Input_CreateKeyState()
            val keycode = platform.input.OH_Input_GetKeyCode(keystate)
    }
}
