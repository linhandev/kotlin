// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 15 -> sentence 15
 *                type-inference, smart-casts -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: smart cast from when is branch does not leak outside the when
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun case_1(e: Expr): Int {
    when (e) {
        is Num -> {}
        else -> {}
    }
    return e.<!UNRESOLVED_REFERENCE!>n<!>
}
