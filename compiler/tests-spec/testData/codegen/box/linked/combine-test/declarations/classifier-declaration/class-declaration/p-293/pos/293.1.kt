/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 293 -> sentence 293
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 293 -> sentence 293
 * NUMBER: 1
 * DESCRIPTION: inner class must be constructed through an outer instance
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner(val v: Int)
}

fun test(): Int = Outer().Inner(2).v

fun box(): String {
    val inner = Outer().Inner(2)
    if (inner.v != 2) return "NOK: v"
    if (test() != 2) return "NOK: test"
    if (Outer().Inner(7).v != 7) return "NOK: direct"
    return "OK"
}
