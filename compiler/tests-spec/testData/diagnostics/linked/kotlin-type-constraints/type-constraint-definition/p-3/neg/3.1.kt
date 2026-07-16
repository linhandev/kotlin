// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, type-constraint-definition -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: implicit kotlin.Nothing <: T does not make arbitrary types subtypes of Nothing
 */

// TESTCASE NUMBER: 1
fun case_1(): Nothing {
    return <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}
