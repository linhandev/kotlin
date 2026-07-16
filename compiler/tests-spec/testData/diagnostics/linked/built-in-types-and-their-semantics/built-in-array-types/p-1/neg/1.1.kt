// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-array-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.Array element type parameter is invariant across assignments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: Array<Int> = arrayOf(1, 2)
    val b: Array<String> = <!TYPE_MISMATCH!>a<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val a: Array<String> = arrayOf("a")
    val b: Array<Any> = <!TYPE_MISMATCH!>a<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val a: Array<Int> = arrayOf(1)
    val b: Array<Number> = <!TYPE_MISMATCH!>a<!>
}
