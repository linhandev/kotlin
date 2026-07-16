// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: decimal integer 4_2 with internal underscore evaluates to 42
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 4_2
    return if (value == 42) "OK" else "NOK"
}
