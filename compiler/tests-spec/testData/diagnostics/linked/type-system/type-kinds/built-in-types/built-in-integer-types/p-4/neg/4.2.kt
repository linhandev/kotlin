// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, built-in-integer-types -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: Floating-point and boolean types cannot be used as built-in signed integer types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: Int? = <!TYPE_MISMATCH!>"test"<!>
    val b: Short? = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1.0<!>
    val c: Byte? = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>true<!>
    val d: Long? = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1.0f<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val arr: IntArray = intArrayOf(1, 2, 3)
    arr[0] = <!TYPE_MISMATCH!>"test"<!>
    val shortArr: ShortArray = shortArrayOf(1, 2)
    shortArr[0] = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1.0<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x: Number = 10
    val i: Int = <!TYPE_MISMATCH!>x <!CAST_NEVER_SUCCEEDS!>as<!> String<!>
    val s: Short = <!TYPE_MISMATCH!>x <!CAST_NEVER_SUCCEEDS!>as<!> String<!>
}


// TESTCASE NUMBER: 4
class Case4(val i: Int, val s: Short, val b: Byte, val l: Long)

fun case_4(c: Case4) {
    val i: Int = <!TYPE_MISMATCH!>c.s<!>
    val s: Short = <!TYPE_MISMATCH!>c.b<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val sum: Long = <!TYPE_MISMATCH!>"bad"<!>
    checkSubtype<Long>(<!TYPE_MISMATCH!>"bad"<!>)
}
