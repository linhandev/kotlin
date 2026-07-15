// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Only kotlin.Nothing values can be assigned to kotlin.Nothing
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: Nothing = <!TYPE_MISMATCH!>"test"<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val b: Nothing = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val c: Nothing = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>true<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val d: Nothing = <!TYPE_MISMATCH!>Unit<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    checkSubtype<Nothing>(<!TYPE_MISMATCH!>Any()<!>)
}