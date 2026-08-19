// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 3 -> sentence 3
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: else makes when on sealed subject usable as an expression
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun test(e: Expr): Int = when (e) {
    is Num -> e.n
    else -> 0
}

fun box(): String {
    if (test(Num(4)) != 4) return "NOK"
    if (test(Add(Num(1), Num(1))) != 0) return "NOK"
    return "OK"
}
