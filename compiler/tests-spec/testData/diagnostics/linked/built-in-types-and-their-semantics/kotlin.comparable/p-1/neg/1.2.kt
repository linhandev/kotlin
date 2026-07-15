// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.comparable -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Comparable is not a subtype of unrelated types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val c: Comparable<Int> = 1
    val i: Int = <!TYPE_MISMATCH!>c<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val a: Comparable<Int> = 1
    val b: Comparable<String> = <!TYPE_MISMATCH!>a<!>
}
