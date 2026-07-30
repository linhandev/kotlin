// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 *                expressions, prefix-expressions, unary-plus-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: class member operator fun unaryPlus desugars to member call in unary plus expression
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun unaryPlus() = Vector(x)
}

fun test(): Vector = +Vector(5)

fun box(): String {
    if (test().x != 5) return "NOK"
    return "OK"
}
