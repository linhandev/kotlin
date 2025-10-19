import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoKeyAgreementApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoKeyAgreementCreate = platform.CryptoKeyAgreementApi.OH_CryptoKeyAgreement_Create("KeyAgreement", null)
            val cryptoKeyAgreementDestroy = platform.CryptoKeyAgreementApi.OH_CryptoKeyAgreement_Destroy(null)
    }
}
