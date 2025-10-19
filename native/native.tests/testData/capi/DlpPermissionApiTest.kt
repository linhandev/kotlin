import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class DlpPermissionApiTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val sandboxAppConfigSet = platform.DlpPermissionApi.OH_DLP_SetSandboxAppConfig(null)
            val sandboxAppConfigClean = platform.DlpPermissionApi.OH_DLP_CleanSandboxAppConfig()
    }
}
