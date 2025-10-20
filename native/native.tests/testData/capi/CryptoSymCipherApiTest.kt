import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoSymCipherApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoSymCipherParamsyCreate = platform.CryptoSymCipherApi.OH_CryptoSymCipherParams_Create(null)
            val cryptoSymCipherParamsyDestroy = platform.CryptoSymCipherApi.OH_CryptoSymCipherParams_Destroy(null)
    }
}
