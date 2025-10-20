import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class NeuralNetworkRuntimeTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val constructcache = platform.NeuralNetworkRuntime.OH_NNCompilation_ConstructForCache()
            val setdevice = platform.NeuralNetworkRuntime.OH_NNCompilation_SetDevice(constructcache, 0uL)
    }
}
