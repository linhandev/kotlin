/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 307 -> sentence 307
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 307 -> sentence 307
 * NUMBER: 1
 * DESCRIPTION: nested class can declare its own independent type parameters
 */

// TESTCASE NUMBER: 1
class Outer {
    class Nested<U>(val u: U)
}

fun test(): String = Outer.Nested("a").u

fun box(): String {
    if (Outer.Nested("a").u != "a") return "NOK: string"
    if (test() != "a") return "NOK: test"
    if (Outer.Nested(1).u != 1) return "NOK: int"
    return "OK"
}
