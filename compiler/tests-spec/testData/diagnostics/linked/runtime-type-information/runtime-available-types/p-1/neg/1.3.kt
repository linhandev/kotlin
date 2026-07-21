// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information, runtime-available-types -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: non-reified type parameter is not runtime-available for type checking
 */

// TESTCASE NUMBER: 1
fun <T> case_1(value: Any) {
    if (value is <!CANNOT_CHECK_FOR_ERASED!>T<!>) {}
}
