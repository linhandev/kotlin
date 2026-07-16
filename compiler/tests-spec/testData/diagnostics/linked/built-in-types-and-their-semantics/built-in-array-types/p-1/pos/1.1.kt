// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-array-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: arrayOf and typed array literals produce kotlin.Array with the expected element type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: Array<Int> = arrayOf(1, 2, 3)
    checkSubtype<Array<Int>>(a)
    a[0] checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val a: Array<String> = arrayOf("a", "b")
    checkSubtype<Array<String>>(a)
    a.size checkType { check<Int>() }
}


// TESTCASE NUMBER: 3
fun case_3() {
    val na: Array<Int>? = null
    checkSubtype<Array<Int>?>(na)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val empty: Array<String> = emptyArray()
    checkSubtype<Array<String>>(empty)
}
