// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 10 -> sentence 10
 * NUMBER: 5
 * DESCRIPTION: HexDigit full range 0-9A-Fa-f in hex literal 0x1234567890abcdefL (with Long suffix L)
 */
// TESTCASE NUMBER: 1
fun box(): String = if (
    0x1234567890abcdefL == 1311768467294899695L
) "OK" else "NOK"