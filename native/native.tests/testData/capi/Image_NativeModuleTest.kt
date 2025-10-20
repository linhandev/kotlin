import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class Image_NativeModuleTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val pictureNativeCreate = platform.Image_NativeModule.OH_PictureNative_CreatePicture(null, null)
            val mainPixelmapGet = platform.Image_NativeModule.OH_PictureNative_GetMainPixelmap(null, null)
    }
}
