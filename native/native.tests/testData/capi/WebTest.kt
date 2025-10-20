import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class WebTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val setLessCache = platform.Web.OH_NativeArkWeb_SetBlanklessLoadingCacheCapacity(30u)
            val saveCookieSync = platform.Web.OH_ArkWebCookieManager_SaveCookieSync()
    }
}
