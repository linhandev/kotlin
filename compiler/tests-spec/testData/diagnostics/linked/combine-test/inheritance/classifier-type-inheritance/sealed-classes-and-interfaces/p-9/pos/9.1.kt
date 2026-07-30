// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
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

fun case_1(e: Expr) {
    when (e) {
        is Num -> {}
        else -> {}
    }
}
