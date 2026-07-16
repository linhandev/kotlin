// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

/**
 * Regression test: .bss slot becomes stale after GC compaction during
 * object singleton constructor.
 *
 * The .bss slot is written before the constructor runs but not yet
 * registered in GlobalsRegistry. If compacting GC moves the instance
 * during the constructor, the unregistered slot still points to the
 * from-space address, causing SIGSEGV on next access.
 *
 * Triggers async heuristic GC via allocation pressure (>20MB threshold).
 */

import kotlin.test.*

object HeavySingleton {
    val name: String
    val data: IntArray

    init {
        name = "test"
        data = IntArray(50) { it }
        // Exceed 20MB heap threshold to trigger async heuristic GC
        // during init window (before InitAndRegisterGlobal)
        for (i in 0 until 500) {
            ByteArray(65536)
        }
    }
}

@Test fun testStaleBssGlobalInit() {
    val s = HeavySingleton
    assertEquals("test", s.name, "BUG: name=${s.name}")
    assertEquals(1225, s.data.sum(), "BUG: sum=${s.data.sum()}")
    println("PASS")
}
