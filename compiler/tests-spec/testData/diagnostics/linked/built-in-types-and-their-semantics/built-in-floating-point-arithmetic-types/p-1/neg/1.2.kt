// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-floating-point-arithmetic-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: built-in floating point compareTo rejects non-matching operands
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    1.0f.<!NONE_APPLICABLE!>compareTo<!>("x")
}


// TESTCASE NUMBER: 2
fun case_2() {
    1.0.<!NONE_APPLICABLE!>compareTo<!>(true)
}


// TESTCASE NUMBER: 3
fun case_3(x: Double) {
    x.<!NONE_APPLICABLE!>compareTo<!>(Any())
}
