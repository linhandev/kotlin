import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class PasteboardSmokeTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
        val OHPasteboardCreate = platform.Pasteboard.OH_Pasteboard_Create()
        val OHPasteboardDestroy = platform.Pasteboard.OH_Pasteboard_Destroy(OHPasteboardCreate)
//
//        //NullPointerException
//        val str: String? = null
//        println(str!!.length)
    }
}