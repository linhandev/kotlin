// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: hexadecimal integer 0x1_2_3 with multiple underscores evaluates to 0x123
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0x1_2_3
    return if (value == 0x123) "OK" else "NOK"
}
