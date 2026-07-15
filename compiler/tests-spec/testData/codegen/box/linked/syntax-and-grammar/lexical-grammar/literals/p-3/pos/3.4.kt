// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 3 -> sentence 3
 * NUMBER: 4
 * DESCRIPTION: DecDigitOrSeparator underscore in long literal 1_000L
 */
// TESTCASE NUMBER: 1
fun box(): String = if (1_000L == 1000L) "OK" else "NOK"
