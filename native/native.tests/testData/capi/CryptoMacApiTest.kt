import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoMacApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoMacCreate = platform.CryptoMacApi.OH_CryptoMac_Create("Mac",null)
            val cryptoMacDestroy = platform.CryptoMacApi.OH_CryptoMac_Destroy(null)
    }
}
