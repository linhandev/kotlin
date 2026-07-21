// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information, runtime-available-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: parameterized type with concrete type arguments is not runtime-available for is
 */

// TESTCASE NUMBER: 1
fun case_1(value: Any) {
    val x = value is <!CANNOT_CHECK_FOR_ERASED!>List<String><!>
}
