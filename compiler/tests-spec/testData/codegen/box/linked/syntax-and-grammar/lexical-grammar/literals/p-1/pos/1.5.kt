// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: DecDigitNoZero as leading digit in float literal 9f
 */
// TESTCASE NUMBER: 1
fun box(): String = if (9f == 9.0f) "OK" else "NOK"
