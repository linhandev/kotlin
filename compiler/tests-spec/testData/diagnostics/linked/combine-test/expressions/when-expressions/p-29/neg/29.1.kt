// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 29 -> sentence 29
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: when expression branch with in operator fails when contains return type is not Boolean
 */

// TESTCASE NUMBER: 1
class Box {
    <!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun contains(x: Int): String = "yes"
}

fun test(x: Int): String = when (x) {
    <!RESULT_TYPE_MISMATCH!>in<!> Box() -> "inside"
    else -> "other"
}
