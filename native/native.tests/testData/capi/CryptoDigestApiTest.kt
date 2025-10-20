import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoDigestApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoDigestCreate = platform.CryptoDigestApi.OH_CryptoDigest_Create("Digest",null)
            val cryptoDigestUpdate = platform.CryptoDigestApi.OH_CryptoDigest_Update(null, null)
    }
}
