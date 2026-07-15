// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 5 -> sentence 5
 * NUMBER: 3
 * DESCRIPTION: DoubleExponent with minus sign in 3.14e-2
 */
// TESTCASE NUMBER: 1
fun box(): String = if (3.14e-2 == 0.0314) "OK" else "NOK"
