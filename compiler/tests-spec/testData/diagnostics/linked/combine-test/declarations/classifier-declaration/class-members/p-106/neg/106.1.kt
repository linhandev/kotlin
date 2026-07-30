// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 106 -> sentence 106
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 106 -> sentence 106
 *                expressions, comparison-expressions -> paragraph 106 -> sentence 106
 * NUMBER: 1
 * DESCRIPTION: class member compareTo returning Boolean is inapplicable as operator
 */

// TESTCASE NUMBER: 1
class Box(val x: Int) {
    <!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun compareTo(other: Box): Boolean = x < other.x
}

fun test(a: Box, b: Box) = a <!COMPARE_TO_TYPE_MISMATCH!><<!> b
