import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class LocationTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val locatingStart = platform.Location.OH_Location_StartLocating(null)
            val locatingStop = platform.Location.OH_Location_StopLocating(null)
    }
}
