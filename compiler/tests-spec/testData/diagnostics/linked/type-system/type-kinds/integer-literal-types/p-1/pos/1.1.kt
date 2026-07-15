// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, integer-literal-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Integer literal types are inferred for decimal, hexadecimal, and long literal forms
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Int = 42
    val y: Short = 1
    val z: Long = 1L
    checkSubtype<Int>(x)
    checkSubtype<Short>(y)
    checkSubtype<Long>(z)
}


// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<Int>(1)
    checkSubtype<Byte>(1)
    checkSubtype<Short>(1)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x: Int = 0xFF
    checkSubtype<Int>(x)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val x: Long = 9223372036854775806L
    checkSubtype<Long>(x)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val values = listOf(1, 2, 3)
    checkSubtype<Int>(values[0])
}
