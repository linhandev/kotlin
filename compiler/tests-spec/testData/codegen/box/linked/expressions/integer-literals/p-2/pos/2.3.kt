// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: hex literal 0xDEADBEEF evaluates to 0xDEADBEEF
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0xDEADBEEF
    return if (value == 0xDEADBEEF) "OK" else "NOK"
}
