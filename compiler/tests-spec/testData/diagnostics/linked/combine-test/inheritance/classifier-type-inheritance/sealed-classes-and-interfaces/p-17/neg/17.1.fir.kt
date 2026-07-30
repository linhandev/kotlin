// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: sealed when branches with incompatible return types fail
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()
data class Add(val l: Expr, val r: Expr) : Expr()

fun case_1(e: Expr): Int = <!RETURN_TYPE_MISMATCH!>when (e) {
    is Num -> e.n
    is Add -> "x"
}<!>
