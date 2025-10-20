import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoSignatureApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoVerifyCreate = platform.CryptoSignatureApi.OH_CryptoVerify_Create("CryptoVerify",null)
            val cryptoVerifyDestroy = platform.CryptoSignatureApi.OH_CryptoVerify_Destroy(null)
    }
}
