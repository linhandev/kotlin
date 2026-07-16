// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: hexadecimal integer 0xFF evaluates to 255
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0xFF
    return if (value == 255) "OK" else "NOK"
}
