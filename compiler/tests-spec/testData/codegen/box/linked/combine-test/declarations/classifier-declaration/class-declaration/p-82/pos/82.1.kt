// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 82 -> sentence 82
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 82 -> sentence 82
 *                declarations, property-declaration -> paragraph 82 -> sentence 82
 * NUMBER: 1
 * DESCRIPTION: primary constructor parameter usable in class body property initializers
 */

// TESTCASE NUMBER: 1
class Wrap(x: Int) { val doubled = x * 2 }

fun test(): Int = Wrap(3).doubled


fun box(): String {
    if (test() != 6) return "NOK"
    return "OK"
}
