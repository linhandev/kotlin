// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 141 -> sentence 141
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 141 -> sentence 141
 * NUMBER: 1
 * DESCRIPTION: labeled return inside init (via run) exits only that scope type inference in class declaration
 * HELPERS: checkType
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

fun case1() {
    val early = Demo(true)
    early checkType { check<Demo>() }
    early.n checkType { check<Int>() }
    early.steps checkType { check<MutableList<String>>() }
    val cont = Demo(false)
    cont checkType { check<Demo>() }
    cont.n checkType { check<Int>() }
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

fun case2() {
    val skipped = Flagged(true)
    skipped checkType { check<Flagged>() }
    skipped.value checkType { check<Int>() }
    skipped.assignedInFirst checkType { check<Boolean>() }
    val assigned = Flagged(false)
    assigned checkType { check<Flagged>() }
    assigned.value checkType { check<Int>() }
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

fun case3() {
    val t = Triple(1)
    t checkType { check<Triple>() }
    t.log checkType { check<MutableList<Int>>() }
    t.result checkType { check<Int>() }
}
