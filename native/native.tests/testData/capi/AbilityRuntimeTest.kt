import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AbilityRuntimeTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val options = platform.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
            val DisplayId = platform.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsDisplayId(options, 1123)
    }
}
