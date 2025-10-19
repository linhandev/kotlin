import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OHIPCRemoteObjectTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val ipcRemoteStubCreate = platform.OHIPCRemoteObject.OH_IPCRemoteStub_Create("IPCRemoteStub",null, null, null)
            val ipcRemoteStubDestroy = platform.OHIPCRemoteObject.OH_IPCRemoteStub_Destroy(ipcRemoteStubCreate)
    }
}
