// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 26 -> sentence 26
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: sealed subclasses are matched by type tests, not enum-like constants
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr
data class Num(val n: Int) : Expr()

fun case_1(e: Expr) {
    checkSubtype<Int>(when (e) {
        is Num -> e.n
    })
}
