// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.boolean -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: logical not operator requires kotlin.Boolean operand
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x = <!UNRESOLVED_REFERENCE!>!<!>1
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x = <!UNRESOLVED_REFERENCE!>!<!>"false"
}


// TESTCASE NUMBER: 3
fun case_3(a: Int) {
    val x = <!UNRESOLVED_REFERENCE!>!<!>a
}


// TESTCASE NUMBER: 4
fun case_4() {
    val x = <!UNRESOLVED_REFERENCE!>!<!>Unit
}


// TESTCASE NUMBER: 5
fun case_5(x: Any) {
    val y = <!UNRESOLVED_REFERENCE!>!<!>x
}
