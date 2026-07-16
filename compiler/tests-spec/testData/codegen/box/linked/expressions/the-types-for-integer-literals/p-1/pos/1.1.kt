// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, the-types-for-integer-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: literals 42L, 0xFFL and 0b1010L with L suffix have type Long
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val dec = 42L
    val hex = 0xFFL
    val bin = 0b1010L
    return if (dec == 42L && hex == 255L && bin == 10L) "OK" else "NOK"
}
