// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.boolean -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Boolean operators require kotlin.Boolean operands
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x = true && <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x = <!TYPE_MISMATCH!>"x"<!> || false
}


// TESTCASE NUMBER: 3
fun case_3(a: Int) {
    val x = <!TYPE_MISMATCH!>a<!> && true
}


// TESTCASE NUMBER: 4
fun case_4(a: Int, b: Int) {
    val x = <!TYPE_MISMATCH!>a<!> && <!TYPE_MISMATCH!>b<!>
}


// TESTCASE NUMBER: 5
fun case_5(x: String) {
    val y = <!TYPE_MISMATCH!>x<!> || true
}
