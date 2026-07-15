// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, integer-literal-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Integer literals that do not fit the expected type produce type mismatch
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val y: Short = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>100000<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val y: Byte = <!TYPE_MISMATCH!>-129<!>
}
