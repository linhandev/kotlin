import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class HuksParamSetApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val husksParamSetInit = platform.HuksParamSetApi.OH_Huks_InitParamSet(null)
            val huksParamsAdd = platform.HuksParamSetApi.OH_Huks_AddParams(null, null, 1u)
    }
}
