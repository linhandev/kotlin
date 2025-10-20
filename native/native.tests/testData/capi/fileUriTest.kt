import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class fileUriTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val uriFromPath = platform.fileUri.OH_FileUri_GetUriFromPath("", 1u, null)
            val pathFromUri = platform.fileUri.OH_FileUri_GetPathFromUri("", 1u, null)
    }
}
