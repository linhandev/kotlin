// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: decimal literal 1234_5678 with internal underscores evaluates to 12345678
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 1234_5678
    return if (value == 12345678) "OK" else "NOK"
}
