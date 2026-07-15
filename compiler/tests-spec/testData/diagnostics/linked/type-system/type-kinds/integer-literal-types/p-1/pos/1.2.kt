// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, integer-literal-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Integer literals are usable where Int and narrower integer types are expected
 * HELPERS: checkType
 */

fun acceptInt(x: Int) {}
fun acceptByte(x: Byte) {}
fun acceptShort(x: Short) {}

// TESTCASE NUMBER: 1
fun case_1() {
    acceptInt(1)
    acceptInt(0xFF)
}


// TESTCASE NUMBER: 2
fun case_2() {
    acceptByte(1)
    acceptShort(1)
}


// TESTCASE NUMBER: 3
fun case_3(): Int = 42


// TESTCASE NUMBER: 4
fun case_4(x: Int = 10) {
    checkSubtype<Int>(x)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val arr = intArrayOf(1, 2, 3)
    checkSubtype<IntArray>(arr)
}
