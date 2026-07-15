// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-integer-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, built-in-types, built-in-integer-types -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: built-in integer arithmetic preserves signed integer types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Int = 1 + 2
    val y: Int = x - 1
    val z: Int = x * 2
    checkSubtype<Int>(x + y + z)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x: Short = (1 + 2).toShort()
    val y: Short = x.inc()
    checkSubtype<Short>(y.dec())
}


// TESTCASE NUMBER: 3
fun case_3() {
    val x: Byte = (10 + 20).toByte()
    val y: Byte = (x + 1.toByte()).toByte()
    checkSubtype<Byte>(y)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val x: Long = 1L + 2L
    val y: Long = x / 2L
    checkSubtype<Long>(y % 3L)
}
