import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class TimeServiceTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val timeZoneGet = platform.TimeService.OH_TimeService_GetTimeZone(null ,10u)
    }
}
