// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 11 -> sentence 11
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 11 -> sentence 11
 *                type-system, introduction-1 -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: nullable sealed when with null and subclass branches is exhaustive
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Nil : Expr()

fun test(e: Expr?): Int = when (e) {
    is Num -> e.n
    is Nil -> 0
    null -> -1
}

fun box(): String {
    if (test(Num(2)) != 2) return "NOK"
    if (test(Nil) != 0) return "NOK"
    if (test(null) != -1) return "NOK"
    return "OK"
}
