// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: decimal integer 1_000 with underscore separator evaluates to 1000
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 1_000
    return if (value == 1000) "OK" else "NOK"
}
