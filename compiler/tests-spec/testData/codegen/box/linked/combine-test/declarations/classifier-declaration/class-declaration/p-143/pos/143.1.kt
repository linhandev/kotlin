// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 143 -> sentence 143
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 143 -> sentence 143
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 143 -> sentence 143
 * NUMBER: 1
 * DESCRIPTION: enum class init block runs once for each enum constant during class declaration initialization
 */

val trackedLog = mutableListOf<String>()

// TESTCASE NUMBER: 1
enum class Tracked {
    A, B, C;

    init {
        trackedLog += name
    }
}

val numberedLog = mutableListOf<String>()

// TESTCASE NUMBER: 2
enum class Numbered(val code: Int) {
    X(1), Y(2);

    val stamped: Int

    init {
        numberedLog += name
        stamped = code * 10
    }
}

val phaseLog = mutableListOf<String>()

// TESTCASE NUMBER: 3
enum class Phase {
    START {
        override fun tag(): String = "s"
    },
    END {
        override fun tag(): String = "e"
    };

    abstract fun tag(): String

    init {
        phaseLog += name
    }
}

fun viaTracked(): List<String> {
    trackedLog.clear()
    val first = Tracked.A
    return listOf(first.name) + trackedLog.toList()
}

fun viaNumbered(): Pair<List<String>, List<Int>> {
    numberedLog.clear()
    val values = listOf(Numbered.X.stamped, Numbered.Y.stamped)
    return numberedLog.toList() to values
}

fun viaPhase(): Pair<List<String>, List<String>> {
    phaseLog.clear()
    val tags = listOf(Phase.START.tag(), Phase.END.tag())
    return phaseLog.toList() to tags
}

fun box(): String {
    val tracked = viaTracked()
    // first access loads enum class; init runs for every constant
    if (tracked != listOf("A", "A", "B", "C")) return "NOK: tracked"
    if (Tracked.B.name != "B" || Tracked.C.ordinal != 2) return "NOK: tracked-members"

    val (nLog, stamps) = viaNumbered()
    if (nLog != listOf("X", "Y")) return "NOK: numbered-log"
    if (stamps != listOf(10, 20)) return "NOK: numbered-stamps"

    val (pLog, tags) = viaPhase()
    if (pLog != listOf("START", "END")) return "NOK: phase-log"
    if (tags != listOf("s", "e")) return "NOK: phase-tags"
    return "OK"
}
