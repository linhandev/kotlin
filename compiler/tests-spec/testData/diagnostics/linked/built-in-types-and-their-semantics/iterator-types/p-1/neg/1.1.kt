// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, iterator-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.Iterator type parameter is invariant across assignments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val it: Iterator<Int> = arrayOf(1).iterator()
    val other: Iterator<String> = <!TYPE_MISMATCH!>it<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val it: Iterator<Int> = arrayOf(1).iterator()
    val other: Iterator<Char> = <!TYPE_MISMATCH!>it<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val it: Iterator<Int> = arrayOf(1).iterator()
    val s: String = <!TYPE_MISMATCH!>it<!>
}
