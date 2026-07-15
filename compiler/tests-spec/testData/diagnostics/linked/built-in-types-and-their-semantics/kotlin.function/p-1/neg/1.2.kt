// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.function -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: function types with different parameter or return types are not interchangeable
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val f: (Int) -> Int = { <!EXPECTED_PARAMETER_TYPE_MISMATCH!>x: String<!> -> x.length }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f: (Int) -> Int = { it + 1 }
    val g: (String) -> Int = <!TYPE_MISMATCH!>f<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val f: (Int) -> String = { it.toString() }
    val g: (Int) -> Int = <!TYPE_MISMATCH!>f<!>
}
