// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 130 -> sentence 130
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 130 -> sentence 130
 *                declarations, property-declaration -> paragraph 130 -> sentence 130
 * NUMBER: 1
 * DESCRIPTION: var property may be reassigned multiple times in init block in class declaration
 */

// TESTCASE NUMBER: 1
class Counter {
    var n = 0

    init {
        n = 1
        n = 2
    }
}

class Accumulator {
    var total = 10

    init {
        total = total + 5
        total = total * 2
    }
}

class Label {
    var tag = "a"

    init {
        tag = "b"
        tag = "c"
    }
}

fun viaCounter(): Int = Counter().n

fun viaAccumulator(): Int = Accumulator().total

fun viaLabel(): String = Label().tag

fun box(): String {
    if (viaCounter() != 2) return "NOK: counter"
    if (viaAccumulator() != 30) return "NOK: accumulator"
    if (viaLabel() != "c") return "NOK: label"
    return "OK"
}
