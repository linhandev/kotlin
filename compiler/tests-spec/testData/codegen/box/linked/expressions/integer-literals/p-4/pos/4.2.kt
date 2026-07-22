// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: single non-zero decimal digit 9 evaluates to 9
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 9
    return if (value == 9) "OK" else "NOK"
}
