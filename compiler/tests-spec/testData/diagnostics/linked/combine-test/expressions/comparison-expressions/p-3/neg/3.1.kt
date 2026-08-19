// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: custom type without compareTo cannot use less-than
 */

// TESTCASE NUMBER: 1
data class Ver(val n: Int)

fun case_1(): Boolean = Ver(1) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!><<!> Ver(2)
