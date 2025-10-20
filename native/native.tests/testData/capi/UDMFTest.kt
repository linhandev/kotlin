import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class UDMFTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
        val udmfDataCreate = platform.UDMF.OH_UdmfData_Create()
        val udmfDataDestroy = platform.UDMF.OH_UdmfData_Destroy(udmfDataCreate)
    }
}
