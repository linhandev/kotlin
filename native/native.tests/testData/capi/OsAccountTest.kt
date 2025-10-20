import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OsAccountTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val osAccountName = platform.OsAccount.OH_OsAccount_GetName(null,10uL)
    }
}
