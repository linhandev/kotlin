import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoCommonApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cruypt = platform.CryptoCommonApi.OH_Crypto_FreeDataBlob(null)
    }
}
