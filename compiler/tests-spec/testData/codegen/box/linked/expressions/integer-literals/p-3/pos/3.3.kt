// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, integer-literals -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: binary literal 0b10101010 evaluates to 170
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value = 0b10101010
    return if (value == 170) "OK" else "NOK"
}
