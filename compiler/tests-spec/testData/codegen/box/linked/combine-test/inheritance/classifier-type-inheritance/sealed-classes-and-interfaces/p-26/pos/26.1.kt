// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 26 -> sentence 26
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: sealed subclasses are matched by type tests, not enum-like constants
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()

fun test(e: Expr): Int = when (e) {
    is Num -> e.n
}

fun box(): String {
    if (test(Num(8)) != 8) return "NOK"
    return "OK"
}
