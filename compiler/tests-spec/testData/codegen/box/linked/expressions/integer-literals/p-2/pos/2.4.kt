// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: hex literal 0x1_A_B_C with internal underscores evaluates to 0x1ABC
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0x1_A_B_C
    return if (value == 0x1ABC) "OK" else "NOK"
}
