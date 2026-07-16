// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: single-digit decimal integer 0 evaluates to zero
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0
    return if (value == 0) "OK" else "NOK"
}
