import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ArkTS_Napi_NativeModuleTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val setNapiNativeModule = platform.ArkTS_Napi_NativeModule.napi_set_element(null,null, 1u,null)
            val getNapiNativeModule = platform.ArkTS_Napi_NativeModule.napi_get_element(null,null, 1u,null)
    }
}
