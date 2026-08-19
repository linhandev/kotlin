// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 25 -> sentence 25
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: exhaustive sealed when assigned to val has branch common type
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
object Nil : Expr()

fun test(e: Expr): Any {
    val r = when (e) {
        is Num -> e.n
        is Nil -> "nil"
    }
    return r
}

fun box(): String {
    if (test(Num(1)) != 1) return "NOK"
    if (test(Nil) != "nil") return "NOK"
    return "OK"
}
