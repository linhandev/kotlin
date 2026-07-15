// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 10 -> sentence 10
 * NUMBER: 2
 * DESCRIPTION: HexDigit uppercase A-F in hex literal 0xA
 */
// TESTCASE NUMBER: 1
fun box(): String = if (0xA == 10) "OK" else "NOK"
