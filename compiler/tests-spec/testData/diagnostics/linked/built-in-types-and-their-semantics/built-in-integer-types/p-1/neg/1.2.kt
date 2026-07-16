// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-integer-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: built-in integer compareTo rejects non-integer operands
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    1.<!NONE_APPLICABLE!>compareTo<!>("x")
}


// TESTCASE NUMBER: 2
fun case_2() {
    1L.<!NONE_APPLICABLE!>compareTo<!>("y")
}


// TESTCASE NUMBER: 3
fun case_3() {
    1.toShort().<!NONE_APPLICABLE!>compareTo<!>(true)
}


// TESTCASE NUMBER: 4
fun case_4() {
    1.toByte().<!NONE_APPLICABLE!>compareTo<!>(Unit)
}


// TESTCASE NUMBER: 5
fun case_5(x: Int) {
    x.<!NONE_APPLICABLE!>compareTo<!>(Any())
}
