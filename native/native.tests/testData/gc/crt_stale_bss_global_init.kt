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

fun main() {
    val s = HeavySingleton
    require(s.name == "test") { "BUG: name=${s.name}" }
    require(s.data.sum() == 1225) { "BUG: sum=${s.data.sum()}" }
    println("OK")
}
