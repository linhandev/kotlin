// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, built-in-integer-types -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Non-integer types cannot be used as built-in signed integer types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Int = <!TYPE_MISMATCH!>"test"<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x: Short = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1.0<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x: Byte = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>true<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val x: Long = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1.0f<!>
}


// TESTCASE NUMBER: 5
fun case_5(x: Int, y: Short, z: Byte, w: Long) {
    checkSubtype<Int>(<!TYPE_MISMATCH!>y<!>)
    checkSubtype<Short>(<!TYPE_MISMATCH!>z<!>)
    checkSubtype<Byte>(<!TYPE_MISMATCH!>w<!>)
    checkSubtype<Long>(<!TYPE_MISMATCH!>x<!>)
}
