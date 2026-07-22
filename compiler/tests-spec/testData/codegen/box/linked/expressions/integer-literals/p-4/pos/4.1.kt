// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: decimal literal 42 evaluates to 42
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 42
    return if (value == 42) "OK" else "NOK"
}
