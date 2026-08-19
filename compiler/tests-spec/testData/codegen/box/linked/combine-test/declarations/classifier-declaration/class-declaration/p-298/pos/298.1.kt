/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 298 -> sentence 298
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 298 -> sentence 298
 * NUMBER: 1
 * DESCRIPTION: nested class construction does not require any outer instance to exist
 */

// TESTCASE NUMBER: 1
class Outer(val id: Int) {
    class Nested
}

fun test(): Boolean {
    Outer.Nested()
    return true
}

fun box(): String {
    if (!test()) return "NOK: test"
    Outer.Nested()
    if (Outer.Nested() !is Outer.Nested) return "NOK: type"
    return "OK"
}
