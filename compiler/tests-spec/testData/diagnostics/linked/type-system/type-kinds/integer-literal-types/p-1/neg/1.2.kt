// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, integer-literal-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Integer literal types cannot be assigned to incompatible non-integer types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Boolean = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}
