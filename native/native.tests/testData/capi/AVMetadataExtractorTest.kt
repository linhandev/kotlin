import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVMetadataExtractorTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val avmetaextra = platform.AVMetadataExtractor.OH_AVMetadataExtractor_Create()
            val desavmetaextra = platform.AVMetadataExtractor.OH_AVMetadataExtractor_Release(avmetaextra)
    }
}
