// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 5 -> sentence 5
 * NUMBER: 5
 * DESCRIPTION: DoubleExponent DecDigits with underscores in 1e1_0
 */
// TESTCASE NUMBER: 1
fun box(): String = if (1e1_0 == 1e10) "OK" else "NOK"
