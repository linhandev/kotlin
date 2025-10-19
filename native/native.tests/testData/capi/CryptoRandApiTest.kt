import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoRandApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoRandCreate = platform.CryptoRandApi.OH_CryptoRand_Create(null)
            val cryptoRandDestroy = platform.CryptoRandApi.OH_CryptoRand_Destroy(null)
    }
}
