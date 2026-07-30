// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 16 -> sentence 16
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: exhaustive sealed when branches return distinct implementations of a common result type
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

sealed interface Result
data class NumberResult(val value: Int) : Result
data class AddResult(val terms: Int) : Result

fun test(e: Expr): Result = when (e) {
    is Num -> NumberResult(e.n)
    is Add -> AddResult(2)
}

fun box(): String {
    val number = test(Num(3))
    if (number !is NumberResult || number.value != 3) return "NOK: number"

    val add = test(Add(Num(1), Num(2)))
    if (add !is AddResult || add.terms != 2) return "NOK: add"

    return "OK"
}
