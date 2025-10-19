import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoAsymKeyApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoKeyGeneratorCreate = platform.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Create("generator", null)
            val cryptoKeyGeneratorGenerate = platform.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Generate(null, null)
    }
}
