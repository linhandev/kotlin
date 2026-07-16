// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-array-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: arrayOf rejects incompatible element types in one array literal
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: Array<Int> = <!TYPE_MISMATCH!>arrayOf(1, "two")<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val a: Array<String> = <!TYPE_MISMATCH!>arrayOf("a", 1)<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val a: Array<Double> = <!TYPE_MISMATCH!>arrayOf(1.0, "x")<!>
}
