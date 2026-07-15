// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: DecDigitOrSeparator underscore in double literal 1_0.5_1
 */
// TESTCASE NUMBER: 1
fun box(): String = if (1_0.5_1.toInt() == 10 && 1_0.5_1.toString() == "10.51") "OK" else "NOK"
