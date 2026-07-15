// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 10 -> sentence 10
 * NUMBER: 4
 * DESCRIPTION: HexDigit mixed case and DecDigit in hex literal 0xAbCd
 */
// TESTCASE NUMBER: 1
fun box(): String = if (0xAbCd == 43981) "OK" else "NOK"
