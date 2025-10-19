import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ArkUI_AccessibilityTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val componentType = platform.ArkUI_Accessibility.OH_ArkUI_AccessibilityElementInfoSetComponentType(null, "11")
            val component = platform.ArkUI_Accessibility.OH_ArkUI_AccessibilityElementInfoSetContents(null, "11")
    }
}
