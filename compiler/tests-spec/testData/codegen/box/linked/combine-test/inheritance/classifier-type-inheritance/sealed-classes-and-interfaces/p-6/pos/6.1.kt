// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 6 -> sentence 6
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: object subclass requires its own is branch for exhaustiveness
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Nil : Expr()

fun test(e: Expr): String = when (e) {
    is Num -> "n"
    is Nil -> "nil"
}

fun box(): String {
    if (test(Num(1)) != "n") return "NOK"
    if (test(Nil) != "nil") return "NOK"
    return "OK"
}
