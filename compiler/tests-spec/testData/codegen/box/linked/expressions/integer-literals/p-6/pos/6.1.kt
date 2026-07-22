// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: hexadecimal integer 0x1_A_F with underscore evaluates to 0x1AF
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0x1_A_F
    return if (value == 0x1AF) "OK" else "NOK"
}
