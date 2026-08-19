/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 300 -> sentence 300
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 300 -> sentence 300
 * NUMBER: 1
 * DESCRIPTION: nested class can access outer companion object members
 */

// TESTCASE NUMBER: 1
class Outer {
    companion object {
        const val K = 10
    }

    class Nested {
        fun k(): Int = K
    }
}

fun test(): Int = Outer.Nested().k()

fun box(): String {
    if (Outer.Nested().k() != 10) return "NOK: k"
    if (test() != 10) return "NOK: test"
    if (Outer.K != 10) return "NOK: companion"
    return "OK"
}
