import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class OHAVSessionTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val avsessionCreate = platform.OHAVSession.OH_AVSession_Create(1u,"session1","bundle1","abilityname1",null)
            val avsessionDestroy = platform.OHAVSession.OH_AVSession_Destroy(null)
    }
}
