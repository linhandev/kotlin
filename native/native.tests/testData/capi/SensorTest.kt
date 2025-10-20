import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class SensorTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val subscriptionIdCreate = platform.Sensor.OH_Sensor_CreateSubscriptionId()
            val subscriptionIdDestroy = platform.Sensor.OH_Sensor_DestroySubscriptionId(subscriptionIdCreate)
    }
}
