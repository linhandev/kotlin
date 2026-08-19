// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 5 -> sentence 5
 *                type-inference, smart-casts -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: is branches can recursively walk nested sealed structure
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun eval(e: Expr): Int = when (e) {
    is Num -> e.n
    is Add -> eval(e.l) + eval(e.r)
}

fun case_1() {
    checkSubtype<Int>(eval(Add(Num(2), Num(3))))
}
