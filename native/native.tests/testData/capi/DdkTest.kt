import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class DdkTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val ddkAshmemCreate = platform.Ddk.OH_DDK_CreateAshmem(null, 1024u, null)
            val ddkAshmemDestroy = platform.Ddk.OH_DDK_DestroyAshmem(null)
    }
}
