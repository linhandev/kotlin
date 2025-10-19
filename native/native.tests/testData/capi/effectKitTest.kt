import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class effectKitTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val filterEffectCreate = platform.effectKit.OH_Filter_CreateEffect(null,null)
            val filterBlur = platform.effectKit.OH_Filter_Blur(null, 1f)
    }
}
