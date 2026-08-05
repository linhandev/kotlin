// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 28 -> sentence 28
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: member contains returning non-Boolean makes operator modifier inapplicable for in-expression
 */

// TESTCASE NUMBER: 1
class Box {
    <!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun contains(x: Int): String = "y"
}

fun case1(): Boolean = 1 <!RESULT_TYPE_MISMATCH!>in<!> Box()
