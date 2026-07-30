// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 20 -> sentence 20
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: matching intermediate sealed subclass exhausts that subtree
 */

// TESTCASE NUMBER: 1
sealed class Expr
sealed class Binary : Expr()
data class Add(val l: Expr, val r: Expr) : Binary()
data class Num(val n: Int) : Expr()

fun test(e: Expr): Int = when (e) {
    is Num -> e.n
    is Binary -> 0
}

fun box(): String {
    if (test(Num(3)) != 3) return "NOK"
    if (test(Add(Num(1), Num(1))) != 0) return "NOK"
    return "OK"
}
