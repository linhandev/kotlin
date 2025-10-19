import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoSymKeyApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoSymCipherCreate = platform.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Create("SymKeyGenerator", null)
            val cryptoSymCipherDestroy = platform.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Destroy(null)
    }
}
