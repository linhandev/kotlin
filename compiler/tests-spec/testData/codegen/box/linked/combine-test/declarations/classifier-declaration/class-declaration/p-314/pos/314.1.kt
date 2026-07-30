/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 314 -> sentence 314
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 314 -> sentence 314
 * NUMBER: 1
 * DESCRIPTION: object declaration nested in a class acts as a singleton nested classifier
 */

// TESTCASE NUMBER: 1
class Outer {
    object Token {
        val v = 1
    }
}

fun test(): Int = Outer.Token.v

fun box(): String {
    if (Outer.Token.v != 1) return "NOK: v"
    if (test() != 1) return "NOK: test"
    if (Outer.Token !== Outer.Token) return "NOK: singleton"
    return "OK"
}
