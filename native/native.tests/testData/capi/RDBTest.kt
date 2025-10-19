import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class RDBTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val rdbCreateConfig = platform.RDB.OH_Rdb_CreateConfig()
            val rdbDestroyConfig = platform.RDB.OH_Rdb_DestroyConfig(rdbCreateConfig)
    }
}
