/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 292 -> sentence 292
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 292 -> sentence 292
 * NUMBER: 1
 * DESCRIPTION: nested class can be constructed without an outer instance
 */

// TESTCASE NUMBER: 1
class Outer {
    class Nested(val v: Int)
}

fun test(): Int = Outer.Nested(1).v

fun box(): String {
    val nested = Outer.Nested(1)
    if (nested.v != 1) return "NOK: v"
    if (test() != 1) return "NOK: test"
    if (Outer.Nested(42).v != 42) return "NOK: direct"
    return "OK"
}
