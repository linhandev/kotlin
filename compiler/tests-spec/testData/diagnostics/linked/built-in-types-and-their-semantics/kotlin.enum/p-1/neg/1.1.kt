// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.enum -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: distinct enum types and kotlin.Int are not subtypes of each other
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E1 { A, B }
enum class E2 { A, B }
fun case_1() {
    val e: E1 = E1.A
    val f: E2 = <!TYPE_MISMATCH!>e<!>
}

// TESTCASE NUMBER: 2
fun case_2() {
    val e: E1 = E1.A
    val i: Int = <!TYPE_MISMATCH!>e<!>
}

// TESTCASE NUMBER: 3
fun case_3() {
    val i: Int = 0
    val e: E1 = <!TYPE_MISMATCH!>i<!>
}

// TESTCASE NUMBER: 4
fun case_4() {
    val s: String = "A"
    val e: E1 = <!TYPE_MISMATCH!>s<!>
}
