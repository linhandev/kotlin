// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-integer-literal-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Out-of-range integer literals fail assignment to narrower types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val b: Byte = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>256<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val b: Byte = <!TYPE_MISMATCH!>-129<!>
}
