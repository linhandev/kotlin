import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class resourcemanagerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val mediaBase64 = platform.resourcemanager.OH_ResourceManager_GetMediaBase64(null, 1u, null, null, 1u)
    }
}
