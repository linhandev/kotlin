import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AssetApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val attri = platform.AssetApi.OH_Asset_Remove(null, 0u)
            val aapi = platform.AssetApi.OH_Asset_Add(null, 0u)
    }
}
