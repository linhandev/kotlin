import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class FileIOTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val fileLocation = platform.FileIO.OH_FileIO_GetFileLocation(null, 1, null)
    }
}
