// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 5 -> sentence 5
 *                expressions, multiplicative-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: class member operator fun rem desugars to member call in multiplicative expression
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun rem(mod: Int) = Vector(x % mod)
}

fun test(): Vector = Vector(7) % 3

fun box(): String {
    if (test().x != 1) return "NOK"
    return "OK"
}
