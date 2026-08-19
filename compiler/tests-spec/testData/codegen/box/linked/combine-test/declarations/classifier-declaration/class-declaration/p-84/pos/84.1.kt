// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 84 -> sentence 84
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 84 -> sentence 84
 *                declarations, property-declaration -> paragraph 84 -> sentence 84
 * NUMBER: 1
 * DESCRIPTION: internal primary constructor usable within same module
 */

// TESTCASE NUMBER: 1
class Secret internal constructor(val code: Int)

fun test(): Secret = Secret(42)


fun box(): String {
    if (test().code != 42) return "NOK"
    return "OK"
}
