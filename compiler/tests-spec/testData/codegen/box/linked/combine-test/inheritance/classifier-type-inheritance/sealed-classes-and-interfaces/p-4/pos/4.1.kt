// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 4 -> sentence 4
 *                type-inference, smart-casts -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: is branch smart-casts sealed subject to data class subclass
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Other : Expr()

fun test(e: Expr): Int = when (e) {
    is Num -> e.n
    else -> 0
}

fun box(): String {
    if (test(Num(5)) != 5) return "NOK"
    if (test(Other) != 0) return "NOK"
    return "OK"
}
