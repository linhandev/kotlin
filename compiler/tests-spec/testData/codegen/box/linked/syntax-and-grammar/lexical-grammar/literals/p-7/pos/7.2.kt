// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 7 -> sentence 7
 * NUMBER: 2
 * DESCRIPTION: FloatLiteral DoubleLiteral with exponent and f suffix 1.5e2f
 */
// TESTCASE NUMBER: 1
fun box(): String = if (1.5e2f == 150.0f) "OK" else "NOK"
