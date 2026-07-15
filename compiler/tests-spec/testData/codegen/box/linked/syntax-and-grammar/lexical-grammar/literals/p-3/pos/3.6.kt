// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 3 -> sentence 3
 * NUMBER: 6
 * DESCRIPTION: Kotlin accepts consecutive DecDigitOrSeparator underscores in 1__2 (parsed as 12)
 */
// TESTCASE NUMBER: 1
fun box(): String = if (1__2 == 12) "OK" else "NOK"
