import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoKdfApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val cryptoKdfParamsCreate = platform.CryptoKdfApi.OH_CryptoKdfParams_Create("KdfParams",null)
            val cryptoKdfParamsSetParam = platform.CryptoKdfApi.OH_CryptoKdfParams_SetParam(null, 1u,null)
    }
}
