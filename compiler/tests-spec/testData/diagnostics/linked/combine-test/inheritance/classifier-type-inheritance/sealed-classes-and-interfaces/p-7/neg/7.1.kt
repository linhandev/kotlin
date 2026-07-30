// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 7 -> sentence 7
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 7 -> sentence 7
 *                type-inference, smart-casts -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: when covering only one sealed subclass is not exhaustive when others exist
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun case_1(e: Expr): Int = <!NO_ELSE_IN_WHEN!>when<!>(e) {
    is Num -> e.n
}
