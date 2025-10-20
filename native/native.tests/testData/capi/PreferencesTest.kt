import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class PreferencesTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val preferencesOptionCreate = platform.Preferences.OH_PreferencesOption_Create()
            val preferencesOptionSetFileName= platform.Preferences.OH_PreferencesOption_SetFileName(preferencesOptionCreate, "test")
    }
}
