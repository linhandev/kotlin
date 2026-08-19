// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 9 -> sentence 9
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: when used as a statement with else covers remaining sealed subclasses
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun test(e: Expr): Int {
    var out = 0
    when (e) {
        is Num -> out = e.n
        else -> out = -1
    }
    return out
}

fun box(): String {
    if (test(Num(7)) != 7) return "NOK"
    if (test(Add(Num(1), Num(1))) != -1) return "NOK"
    return "OK"
}
