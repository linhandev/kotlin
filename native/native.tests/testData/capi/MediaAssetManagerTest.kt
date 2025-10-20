import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class MediaAssetManagerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val mediaassetManager = platform.MediaAssetManager.OH_MediaAssetManager_Create()
            val desmediaassetManager = platform.MediaAssetManager.OH_MediaAssetManager_Release(mediaassetManager)
    }
}
