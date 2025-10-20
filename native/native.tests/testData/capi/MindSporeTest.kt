import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class MindSporeTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val AIcontext = platform.MindSpore.OH_AI_ContextCreate()
            val AImodel = platform.MindSpore.OH_AI_ModelCreate()
    }
}
