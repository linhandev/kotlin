/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 308 -> sentence 308
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 308 -> sentence 308
 * NUMBER: 1
 * DESCRIPTION: multi-level nested qualification via Outer.Middle.Inner
 */

// TESTCASE NUMBER: 1
class Outer {
    class Middle {
        class Inner(val v: Int)
    }
}

fun test(): Int = Outer.Middle.Inner(1).v

fun box(): String {
    if (Outer.Middle.Inner(1).v != 1) return "NOK: v"
    if (test() != 1) return "NOK: test"
    if (Outer.Middle.Inner(9).v != 9) return "NOK: direct"
    return "OK"
}
