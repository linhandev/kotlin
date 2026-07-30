// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 1 -> sentence 1
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: when on sealed class is exhaustive when all direct subclasses are covered
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun test(e: Expr): Int = when (e) {
    is Num -> e.n
    is Add -> 0
}

fun box(): String {
    if (test(Num(3)) != 3) return "NOK"
    if (test(Add(Num(1), Num(2))) != 0) return "NOK"
    return "OK"
}
