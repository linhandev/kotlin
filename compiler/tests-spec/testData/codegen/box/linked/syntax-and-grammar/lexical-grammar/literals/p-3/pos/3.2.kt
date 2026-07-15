// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: DecDigitOrSeparator multiple underscores in integer literal 1_234_567
 */
// TESTCASE NUMBER: 1
fun box(): String = if (1_234_567 == 1234567) "OK" else "NOK"
