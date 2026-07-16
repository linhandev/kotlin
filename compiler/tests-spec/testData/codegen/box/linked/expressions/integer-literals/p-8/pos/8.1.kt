// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: decimal literal 12345 evaluates to 12345
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 12345
    return if (value == 12345) "OK" else "NOK"
}
