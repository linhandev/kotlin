import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class NativeColorSpaceManagerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val colorCreateFromName =  platform.NativeColorSpaceManager.OH_NativeColorSpaceManager_CreateFromName(123213u)
            val colorGetSpaceName =  platform.NativeColorSpaceManager.OH_NativeColorSpaceManager_GetColorSpaceName(colorCreateFromName)
    }
}
