// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: decimal literal 1234567890 evaluates to itself
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 1234567890
    return if (value == 1234567890) "OK" else "NOK"
}
