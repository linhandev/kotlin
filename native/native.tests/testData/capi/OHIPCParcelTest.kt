import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OHIPCParcelTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val IPCParcelCreate = platform.OHIPCParcel.OH_IPCParcel_Create()
            val IPCParcelDestroy = platform.OHIPCParcel.OH_IPCParcel_Destroy(IPCParcelCreate)
    }
}
