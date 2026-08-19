// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 21 -> sentence 21
 *                type-inference, smart-casts -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: !is concrete subclass then else branch smart-casts to remaining sealed type
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun test(e: Expr): Int = when (e) {
    !is Num -> 0
    else -> e.n
}

fun box(): String {
    if (test(Num(4)) != 4) return "NOK"
    if (test(Add(Num(1), Num(1))) != 0) return "NOK"
    return "OK"
}
