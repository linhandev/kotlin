// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: binary integer 0b1_0_1 with multiple underscores evaluates to 5
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0b1_0_1
    return if (value == 5) "OK" else "NOK"
}
