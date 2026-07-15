// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: DecDigitNoZero digits 1 through 9 in single-digit integer literals
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val digits = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    return if (digits == listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)) "OK" else "NOK"
}
