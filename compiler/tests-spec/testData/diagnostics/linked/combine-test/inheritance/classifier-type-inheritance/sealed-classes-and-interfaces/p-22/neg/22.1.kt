// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 22 -> sentence 22
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: adding a new direct sealed subclass makes an old when non-exhaustive
 */

// TESTCASE NUMBER: 1
sealed class Ver
data class V1(val n: Int) : Ver()
data class V2(val n: Int) : Ver()

fun case_1(v: Ver): Int = <!NO_ELSE_IN_WHEN!>when<!>(v) {
    is V1 -> v.n
}
