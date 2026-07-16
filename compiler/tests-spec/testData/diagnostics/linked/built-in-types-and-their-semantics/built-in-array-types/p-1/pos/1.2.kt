// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-array-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Array size constructor, set operator and indexed get produce expected element types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a = Array(3) { it }
    checkSubtype<Array<Int>>(a)
    a.size checkType { check<Int>() }
    a[0] checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val a = arrayOf(0, 0, 0)
    a[1] = 42
    a[1] checkType { check<Int>() }
    checkSubtype<Unit>(a.set(2, 7))
}


// TESTCASE NUMBER: 3
fun case_3() {
    val a = Array(2) { "x$it" }
    a[0] = "a"
    checkSubtype<String>(a[1])
}
