// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.function -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: non-function values cannot be assigned to kotlin.Function types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val f: (Int) -> Int = <!TYPE_MISMATCH!>"not a function"<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f: () -> Unit = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val f: (Int) -> Int = { it + 1 }
    val s: String = <!TYPE_MISMATCH!>f<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val f: Function1<Int, String> = { it: Int -> it.toString() }
    val i: Int = <!TYPE_MISMATCH!>f<!>
}
