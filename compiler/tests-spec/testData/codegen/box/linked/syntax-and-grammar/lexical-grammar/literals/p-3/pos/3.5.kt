// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 3 -> sentence 3
 * NUMBER: 5
 * DESCRIPTION: DecDigitOrSeparator underscore in float literal 1_000f
 */
// TESTCASE NUMBER: 1
fun box(): String = if (1_000f == 1000.0f) "OK" else "NOK"
