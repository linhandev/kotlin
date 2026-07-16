// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, the-types-for-integer-literals -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: hex literal 0x01 is assignable to Byte Short Int and Long
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val asByte: Byte = 0x01
    val asShort: Short = 0x01
    val asInt: Int = 0x01
    val asLong: Long = 0x01
    return if (asByte == 1.toByte() && asShort == 1.toShort() && asInt == 1 && asLong == 1L) "OK" else "NOK"
}
