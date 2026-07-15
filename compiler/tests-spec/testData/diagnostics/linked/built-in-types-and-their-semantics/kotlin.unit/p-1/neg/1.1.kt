// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.unit -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Non-Unit values cannot be used where kotlin.Unit is expected
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val u: Unit = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val u: Unit = <!TYPE_MISMATCH!>"x"<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val u: Unit = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>true<!>
}


// TESTCASE NUMBER: 4
fun case_4(): Unit {
    return <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    checkSubtype<Unit>(<!TYPE_MISMATCH!>Any()<!>)
}
