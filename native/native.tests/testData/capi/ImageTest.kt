import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ImageTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val imageSourceCreateFromUri = platform.Image.OH_ImageSource_CreateFromUri(null,null, 1u, null,null)
            val imageSourceCreateFromFd = platform.Image.OH_ImageSource_CreateFromFd(null,1, null,null)
    }
}
