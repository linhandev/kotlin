/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 295 -> sentence 295
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 295 -> sentence 295
 * NUMBER: 1
 * DESCRIPTION: inner class can be constructed directly inside an outer instance method
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner(val v: Int)

    fun make(): Inner = Inner(1)
}

fun test(): Int = Outer().make().v

fun box(): String {
    val outer = Outer()
    val inner = outer.make()
    if (inner.v != 1) return "NOK: v"
    if (test() != 1) return "NOK: test"
    if (Outer().make().v != 1) return "NOK: direct"
    return "OK"
}
