import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class fileShareTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val fileSharePP = platform.fileShare.OH_FileShare_PersistPermission(null, 1u, null,null)
            val fileShareAP = platform.fileShare.OH_FileShare_ActivatePermission(null, 1u, null,null)
    }
}
