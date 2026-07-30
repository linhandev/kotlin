// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 24 -> sentence 24
 *                type-inference, smart-casts -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: subject-less when can still use is checks on sealed values
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Nil : Expr()

fun test(e: Expr): String = when {
    e is Num -> "n"
    e is Nil -> "z"
    else -> "?"
}

fun box(): String {
    if (test(Num(1)) != "n") return "NOK"
    if (test(Nil) != "z") return "NOK"
    return "OK"
}
