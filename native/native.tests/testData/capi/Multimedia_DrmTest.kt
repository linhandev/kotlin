import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class Multimedia_DrmTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val AVcencifo = platform.Multimedia_Drm.OH_AVCencInfo_Create()
            val desAVcencifo = platform.Multimedia_Drm.OH_AVCencInfo_Destroy(AVcencifo)
    }
}
