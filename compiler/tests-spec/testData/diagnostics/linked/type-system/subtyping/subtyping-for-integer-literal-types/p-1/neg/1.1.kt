// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-integer-literal-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Integer literal cannot substitute for incompatible non-integer types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val s: String = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val a: Array<Int> = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}
