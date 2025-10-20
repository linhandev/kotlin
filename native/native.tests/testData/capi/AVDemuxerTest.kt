import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVDemuxerTest {
    @Test
    fun runTest() {
        // 只验证调用流程不崩溃
            val avdemuxer = platform.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val desavdemuxer = platform.AVDemuxer.OH_AVDemuxer_SelectTrackByID(null, 1u)
    }
}
