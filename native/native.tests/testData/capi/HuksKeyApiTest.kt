import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class HuksKeyApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val husksdkver = platform.HuksKeyApi.OH_Huks_GetSdkVersion(null)
            val huskkeyItem = platform.HuksKeyApi.OH_Huks_GenerateKeyItem(null, null, null)
    }
}
