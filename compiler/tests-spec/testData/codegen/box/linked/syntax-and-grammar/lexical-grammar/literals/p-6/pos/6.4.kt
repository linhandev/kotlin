// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 6 -> sentence 6
 * NUMBER: 4
 * DESCRIPTION: RealLiteral as FloatLiteral from DecDigits 42F
 */
// TESTCASE NUMBER: 1
fun box(): String = if (42F == 42.0f) "OK" else "NOK"
