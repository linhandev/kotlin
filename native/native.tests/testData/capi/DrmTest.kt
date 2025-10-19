import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class DrmTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val mediaKeyRequest = platform.Drm.OH_MediaKeySession_GenerateMediaKeyRequest(null, null, null)
            val mediaKeyRequestProcess = platform.Drm.OH_MediaKeySession_ProcessMediaKeyResponse(null, null, 1,null,null)
    }
}
