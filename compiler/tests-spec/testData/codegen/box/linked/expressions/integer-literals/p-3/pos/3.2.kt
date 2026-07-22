// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: binary integer 0b1 evaluates to 1
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0b1
    return if (value == 1) "OK" else "NOK"
}
