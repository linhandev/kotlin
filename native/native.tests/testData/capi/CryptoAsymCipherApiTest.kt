import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoAsymCipherApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoCreate = platform.CryptoAsymCipherApi.OH_CryptoSm2CiphertextSpec_Create(null, null)
            val cryptoGetItem = platform.CryptoAsymCipherApi.OH_CryptoSm2CiphertextSpec_GetItem(null, 1u, null)
    }
}
