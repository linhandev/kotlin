/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 313 -> sentence 313
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 313 -> sentence 313
 * NUMBER: 1
 * DESCRIPTION: companion object can declare a nested class accessible via Outer.Nested
 */

// TESTCASE NUMBER: 1
class Outer {
    companion object {
        class Nested(val v: Int)
    }
}

fun test(): Int = Outer.Companion.Nested(1).v

fun box(): String {
    if (Outer.Companion.Nested(1).v != 1) return "NOK: nested"
    if (test() != 1) return "NOK: test"
    if (Outer.Companion.Nested(2).v != 2) return "NOK: companion"
    return "OK"
}
