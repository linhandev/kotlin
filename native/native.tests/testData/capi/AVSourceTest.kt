import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVSourceTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val sourceCreateWithURI = platform.AVSource.OH_AVSource_CreateWithURI(null)
            val sourceDestroy = platform.AVSource.OH_AVSource_Destroy(sourceCreateWithURI)
    }
}
