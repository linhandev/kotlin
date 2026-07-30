// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 16 -> sentence 16
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: exhaustive sealed when infers a common result type for distinct branch implementations
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

sealed interface Result
data class NumberResult(val value: Int) : Result
data class AddResult(val terms: Int) : Result

fun case_1(e: Expr) {
    checkSubtype<Result>(when (e) {
        is Num -> NumberResult(e.n)
        is Add -> AddResult(2)
    })
}
