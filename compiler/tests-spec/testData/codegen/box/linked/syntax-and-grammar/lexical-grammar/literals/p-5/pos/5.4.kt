// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 5 -> sentence 5
 * NUMBER: 4
 * DESCRIPTION: DoubleExponent without optional sign in 5E5
 */
// TESTCASE NUMBER: 1
fun box(): String = if (5E5.toLong() == 500000L && 5E5.toString() == "500000.0") "OK" else "NOK"
