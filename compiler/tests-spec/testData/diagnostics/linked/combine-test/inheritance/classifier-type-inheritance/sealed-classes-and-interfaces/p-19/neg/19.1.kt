// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 19 -> sentence 19
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: matching only an open intermediate subclass leaf does not exhaust sealed subject
 */

// TESTCASE NUMBER: 1
sealed class Expr
open class Binary : Expr()
data class Add(val l: Expr, val r: Expr) : Binary()
data class Num(val n: Int) : Expr()

fun case_1(e: Expr): Int = <!NO_ELSE_IN_WHEN!>when<!>(e) {
    is Num -> e.n
    is Add -> 0
}
