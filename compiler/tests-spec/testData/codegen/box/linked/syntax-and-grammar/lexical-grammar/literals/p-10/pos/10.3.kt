// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 10 -> sentence 10
 * NUMBER: 3
 * DESCRIPTION: HexDigit lowercase a-f in hex literal 0xf
 */
// TESTCASE NUMBER: 1
fun box(): String = if (0xf == 15) "OK" else "NOK"
