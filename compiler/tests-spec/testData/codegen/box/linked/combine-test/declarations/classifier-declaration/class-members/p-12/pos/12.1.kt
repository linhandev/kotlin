// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 12 -> sentence 12
 *                expressions, prefix-expressions, logical-not-expressions -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: class member operator fun not desugars to member call in logical not expression
 */

// TESTCASE NUMBER: 1
class Flag(val value: Boolean) {
    operator fun not() = Flag(!value)
}

fun test(): Flag = !Flag(true)

fun box(): String {
    if (test().value != false) return "NOK"
    return "OK"
}
