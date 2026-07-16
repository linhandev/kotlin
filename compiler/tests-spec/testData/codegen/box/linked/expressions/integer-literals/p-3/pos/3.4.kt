// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 3 -> sentence 3
 * NUMBER: 4
 * DESCRIPTION: binary literal 0b1_0_1_0 with internal underscores evaluates to 10
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0b1_0_1_0
    return if (value == 10) "OK" else "NOK"
}
