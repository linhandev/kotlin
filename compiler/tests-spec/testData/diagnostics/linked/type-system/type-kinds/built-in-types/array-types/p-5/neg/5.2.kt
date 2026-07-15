// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, array-types -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: kotlin.Array(T) element assignment rejects unrelated element types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val arr = arrayOfNulls<String>(2)
    arr[0] = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val arr = emptyArray<Long>()
    arr.set(0, <!TYPE_MISMATCH!>"test"<!>)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val arr = Array(3) { it * 2 }
    arr[0] = <!TYPE_MISMATCH!>"bad"<!>
    arr[2] = <!TYPE_MISMATCH!>arr[1].toString()<!>
}


// TESTCASE NUMBER: 4
fun case_4(value: String): Array<String> {
    return arrayOf(value)
}

fun case_4_use() {
    val arr = case_4("test")
    arr[0] = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val arr: Array<out String> = arrayOf("a", "b")
    arr[0] = <!TYPE_MISMATCH!>"x"<!>
}
