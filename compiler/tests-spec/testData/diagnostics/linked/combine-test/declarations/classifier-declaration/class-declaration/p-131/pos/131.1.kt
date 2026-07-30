// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 131 -> sentence 131
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 131 -> sentence 131
 *                declarations, classifier-declaration, object-declaration -> paragraph 131 -> sentence 131
 * NUMBER: 1
 * DESCRIPTION: init block runs when object singleton is first created type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
object O {
    val log = mutableListOf<String>()

    init {
        log += "ready"
    }
}

fun case1() {
    O checkType { check<O>() }
    O.log checkType { check<MutableList<String>>() }
}

// TESTCASE NUMBER: 2
object Tagged {
    val events = mutableListOf<String>()

    init {
        events += "boot"
        events += "done"
    }
}

fun case2() {
    Tagged checkType { check<Tagged>() }
    Tagged.events checkType { check<MutableList<String>>() }
}

// TESTCASE NUMBER: 3
object Counter {
    var hits = 0

    init {
        hits = 1
    }
}

fun case3() {
    Counter checkType { check<Counter>() }
    Counter.hits checkType { check<Int>() }
}
