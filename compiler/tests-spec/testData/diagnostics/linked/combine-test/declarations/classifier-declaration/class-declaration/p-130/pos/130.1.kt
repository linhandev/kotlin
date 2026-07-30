// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 130 -> sentence 130
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 130 -> sentence 130
 *                declarations, property-declaration -> paragraph 130 -> sentence 130
 * NUMBER: 1
 * DESCRIPTION: var property may be reassigned multiple times in init block type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Counter {
    var n = 0

    init {
        n = 1
        n = 2
    }
}

fun case1() {
    val viaCounter = Counter()
    viaCounter checkType { check<Counter>() }
    viaCounter.n checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class Accumulator {
    var total = 10

    init {
        total = total + 5
        total = total * 2
    }
}

fun case2() {
    val viaAccumulator = Accumulator()
    viaAccumulator checkType { check<Accumulator>() }
    viaAccumulator.total checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
class Label {
    var tag = "a"

    init {
        tag = "b"
        tag = "c"
    }
}

fun case3() {
    val viaLabel = Label()
    viaLabel checkType { check<Label>() }
    viaLabel.tag checkType { check<String>() }
}
