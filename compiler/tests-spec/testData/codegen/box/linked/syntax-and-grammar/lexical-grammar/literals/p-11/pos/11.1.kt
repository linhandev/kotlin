// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: HexDigitOrSeparator underscore in HexLiteral 0x1_A_F
 */
// TESTCASE NUMBER: 1
fun box(): String = if (0x1_A_F == 0x1AF) "OK" else "NOK"
