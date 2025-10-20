import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OHIPCSkeletonTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val firstTokenId = platform.OHIPCSkeleton.OH_IPCSkeleton_GetFirstTokenId()
            val selfTokenId = platform.OHIPCSkeleton.OH_IPCSkeleton_GetSelfTokenId()
    }
}
