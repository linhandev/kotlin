// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: compareTo returning non-Int makes less-than unavailable
 */

// TESTCASE NUMBER: 1
data class Ver(val n: Int) {
    <!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun compareTo(other: Ver): String = "x"
}

fun case_1(): Boolean = Ver(1) <!COMPARE_TO_TYPE_MISMATCH!><<!> Ver(2)
