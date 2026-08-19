// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 141 -> sentence 141
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 141 -> sentence 141
 * NUMBER: 1
 * DESCRIPTION: labeled return inside init (via run) exits only that scope; subsequent init blocks still run in class declaration
 */

// TESTCASE NUMBER: 1
class Demo(val early: Boolean) {
    val steps = mutableListOf<String>()
    val n: Int

    init {
        steps += "A"
        run {
            if (early) return@run
            steps += "A-cont"
        }
    }

    init {
        steps += "B"
        n = 2
    }
}

// TESTCASE NUMBER: 2
class Flagged(val skip: Boolean) {
    var assignedInFirst = false
    val value: Int

    init {
        run {
            if (skip) return@run
            assignedInFirst = true
        }
    }

    init {
        value = if (assignedInFirst) 1 else 2
    }
}

// TESTCASE NUMBER: 3
class Triple(val stopAt: Int) {
    val log = mutableListOf<Int>()
    val result: Int

    init {
        log += 1
        run {
            if (stopAt == 1) return@run
            log += 11
        }
    }

    init {
        log += 2
        run {
            if (stopAt == 2) return@run
            log += 22
        }
    }

    init {
        log += 3
        result = log.sum()
    }
}

fun viaDemoEarly(): Pair<List<String>, Int> {
    val d = Demo(true)
    return d.steps to d.n
}

fun viaDemoContinue(): Pair<List<String>, Int> {
    val d = Demo(false)
    return d.steps to d.n
}

fun viaFlaggedSkip(): Pair<Boolean, Int> {
    val f = Flagged(true)
    return f.assignedInFirst to f.value
}

fun viaFlaggedAssign(): Pair<Boolean, Int> {
    val f = Flagged(false)
    return f.assignedInFirst to f.value
}

fun viaTripleStop1(): Pair<List<Int>, Int> {
    val t = Triple(1)
    return t.log to t.result
}

fun viaTripleStop2(): Pair<List<Int>, Int> {
    val t = Triple(2)
    return t.log to t.result
}

fun viaTripleNoStop(): Pair<List<Int>, Int> {
    val t = Triple(0)
    return t.log to t.result
}

fun box(): String {
    if (viaDemoEarly() != (listOf("A", "B") to 2)) return "NOK: demo-early"
    if (viaDemoContinue() != (listOf("A", "A-cont", "B") to 2)) return "NOK: demo-continue"
    if (viaFlaggedSkip() != (false to 2)) return "NOK: flagged-skip"
    if (viaFlaggedAssign() != (true to 1)) return "NOK: flagged-assign"
    if (viaTripleStop1() != (listOf(1, 2, 22, 3) to 28)) return "NOK: triple-1"
    if (viaTripleStop2() != (listOf(1, 11, 2, 3) to 17)) return "NOK: triple-2"
    if (viaTripleNoStop() != (listOf(1, 11, 2, 22, 3) to 39)) return "NOK: triple-0"
    return "OK"
}
