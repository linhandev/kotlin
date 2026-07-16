// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.char -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, integer-literal-types -> paragraph 1 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: kotlin.Char is not a subtype of kotlin.Int or kotlin.String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val c: Char = 'a'
    val i: Int = <!TYPE_MISMATCH!>c<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val i: Int = 65
    val c: Char = <!TYPE_MISMATCH!>i<!>
}

// TESTCASE NUMBER: 3
fun case_3() {
    val s: String = "a"
    val c: Char = <!TYPE_MISMATCH!>s<!>
}

// TESTCASE NUMBER: 4
fun case_4() {
    val b: Boolean = true
    val c: Char = <!TYPE_MISMATCH!>b<!>
}
