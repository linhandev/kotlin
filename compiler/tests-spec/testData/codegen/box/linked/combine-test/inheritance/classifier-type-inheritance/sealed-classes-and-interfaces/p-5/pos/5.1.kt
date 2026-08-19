// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 5 -> sentence 5
 *                type-inference, smart-casts -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: is branches can recursively walk nested sealed structure
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun eval(e: Expr): Int = when (e) {
    is Num -> e.n
    is Add -> eval(e.l) + eval(e.r)
}

fun box(): String {
    if (eval(Add(Num(2), Num(3))) != 5) return "NOK"
    return "OK"
}
