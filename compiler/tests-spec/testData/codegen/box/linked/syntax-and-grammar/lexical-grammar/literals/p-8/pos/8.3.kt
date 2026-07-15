// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 8 -> sentence 8
 * NUMBER: 3
 * DESCRIPTION: DoubleLiteral with underscores in DecDigits 1_0.5_0
 */
// TESTCASE NUMBER: 1
fun box(): String = if (1_0.5_0 == 10.50) "OK" else "NOK"
