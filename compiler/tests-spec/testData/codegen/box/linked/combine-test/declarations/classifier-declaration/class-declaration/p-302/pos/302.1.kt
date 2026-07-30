/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 302 -> sentence 302
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 302 -> sentence 302
 * NUMBER: 1
 * DESCRIPTION: nested class declared in an interface is static and needs no outer instance
 */

// TESTCASE NUMBER: 1
interface I {
    class Nested
}

fun test(): I.Nested = I.Nested()

fun box(): String {
    val nested = I.Nested()
    if (nested !is I.Nested) return "NOK: type"
    if (test() !is I.Nested) return "NOK: test"
    return "OK"
}
