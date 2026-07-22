// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: hexadecimal integer 0xA evaluates to 10
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0xA
    return if (value == 10) "OK" else "NOK"
}
