// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: DecDigitOrSeparator underscore in integer literal 1_000
 */
// TESTCASE NUMBER: 1
fun box(): String = if (1_000 == 1000) "OK" else "NOK"
