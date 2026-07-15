// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.string -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.String is not a subtype of kotlin.Char or kotlin.Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val s: String = "a"
    val c: Char = <!TYPE_MISMATCH!>s<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val s: String = "1"
    val i: Int = <!TYPE_MISMATCH!>s<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val c: Char = 'a'
    val s: String = <!TYPE_MISMATCH!>c<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val i: Int = 1
    val s: String = <!TYPE_MISMATCH!>i<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val b: Boolean = true
    val s: String = <!TYPE_MISMATCH!>b<!>
}
