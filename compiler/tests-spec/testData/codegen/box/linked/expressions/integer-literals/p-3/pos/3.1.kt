// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: binary integer 0b1010 evaluates to 10
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0b1010
    return if (value == 10) "OK" else "NOK"
}
