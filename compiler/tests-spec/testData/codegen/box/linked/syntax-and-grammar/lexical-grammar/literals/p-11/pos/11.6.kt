// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 * NUMBER: 6
 * DESCRIPTION: Kotlin accepts consecutive HexDigitOrSeparator underscores in 0x1__2 (parsed as 0x12)
 */
// TESTCASE NUMBER: 1
fun box(): String = if (0x1__2 == 0x12) "OK" else "NOK"
