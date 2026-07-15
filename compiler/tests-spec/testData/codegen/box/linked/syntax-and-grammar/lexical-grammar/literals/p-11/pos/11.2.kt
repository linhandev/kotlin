// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 * NUMBER: 2
 * DESCRIPTION: BinLiteral with BinDigitOrSeparator 0b1_0_1
 */
// TESTCASE NUMBER: 1
fun box(): String = if (0b1_0_1 == 5) "OK" else "NOK"
