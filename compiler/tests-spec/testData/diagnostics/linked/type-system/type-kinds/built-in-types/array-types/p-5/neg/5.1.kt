// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, array-types -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: kotlin.Array(T) get/set operations require element type T
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val arr = arrayOf("a")
    arr[0] = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val arr = arrayOf(1, 2)
    arr[0] = <!TYPE_MISMATCH!>"test"<!>
}


// TESTCASE NUMBER: 3
class Case3

fun case_3() {
    val arr = arrayOf(Case3(), Case3())
    arr[0] = <!TYPE_MISMATCH!>""<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val arr: Array<String?> = arrayOf(null, "x")
    arr[0] = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
    arr.set(1, <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
}


// TESTCASE NUMBER: 5
fun case_5(): Array<Double> {
    val arr = arrayOf(1.0, 2.0)
    arr[0] = <!TYPE_MISMATCH!>"bad"<!>
    return arr
}

fun case_5_use(arr: Array<Double>) {
    arr[0] = <!TYPE_MISMATCH!>"bad"<!>
}
