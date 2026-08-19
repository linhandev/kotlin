// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 10 -> sentence 10
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 10 -> sentence 10
 *                type-system, introduction-1 -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: nullable sealed when expression requires a null branch
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()

fun case_1(e: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(e) {
    is Num -> e.n
}
