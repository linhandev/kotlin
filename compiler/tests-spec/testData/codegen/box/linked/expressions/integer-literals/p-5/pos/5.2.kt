// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: decimal integer 1_234_567 with multiple underscores evaluates to 1234567
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 1_234_567
    return if (value == 1234567) "OK" else "NOK"
}
