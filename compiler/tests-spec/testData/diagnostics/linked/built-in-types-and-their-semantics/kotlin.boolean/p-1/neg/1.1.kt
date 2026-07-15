// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.boolean -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Non-Boolean values cannot be assigned to kotlin.Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val b: Boolean = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val b: Boolean = <!TYPE_MISMATCH!>"true"<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val b: Boolean = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1.0<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val b: Boolean = <!TYPE_MISMATCH!>Unit<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    checkSubtype<Boolean>(<!TYPE_MISMATCH!>Any()<!>)
}
