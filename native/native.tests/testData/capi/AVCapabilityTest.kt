import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVCapabilityTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val biliName = platform.AVCapability.OH_AVCapability_GetName(null)
            val biliMaxSupportedInstances = platform.AVCapability.OH_AVCapability_GetMaxSupportedInstances(null)
    }
}
