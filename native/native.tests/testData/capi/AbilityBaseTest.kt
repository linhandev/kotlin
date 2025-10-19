import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AbilityBaseTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val want = platform.AbilityBase.OH_AbilityBase_DestroyWant(null)
            val wantSet = platform.AbilityBase.OH_AbilityBase_SetWantCharParam(null, "", "")
    }
}
