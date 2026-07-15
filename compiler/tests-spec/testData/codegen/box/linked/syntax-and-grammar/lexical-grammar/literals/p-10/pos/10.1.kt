// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: HexDigit DecDigit zero in hex literal 0x0
 */
// TESTCASE NUMBER: 1
fun box(): String = if (0x0.toString() == "0" && 0x0 == 0) "OK" else "NOK"
