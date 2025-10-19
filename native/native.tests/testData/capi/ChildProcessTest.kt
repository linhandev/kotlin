import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ChildProcessTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cp = platform.ChildProcess.OH_Ability_CreateChildProcessConfigs();
            val descp = platform.ChildProcess.OH_Ability_DestroyChildProcessConfigs(cp);
    }
}
