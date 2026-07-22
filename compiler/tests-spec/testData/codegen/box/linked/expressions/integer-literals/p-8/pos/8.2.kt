// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 8 -> sentence 8
 * NUMBER: 2
 * DESCRIPTION: single-digit decimal integer 7 evaluates to 7
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 7
    return if (value == 7) "OK" else "NOK"
}
