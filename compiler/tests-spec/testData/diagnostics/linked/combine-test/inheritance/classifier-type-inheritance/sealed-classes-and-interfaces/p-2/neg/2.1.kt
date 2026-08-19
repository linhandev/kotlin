// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 2 -> sentence 2
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: when on sealed class missing a direct subclass is not exhaustive
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()
object Zero : Expr()

fun case_1(e: Expr): Int = <!NO_ELSE_IN_WHEN!>when<!>(e) {
    is Num -> e.n
    is Add -> 0
}
