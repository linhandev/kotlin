// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: UnicodeDigit Arabic-Indic digit in identifier count٤
 */
// TESTCASE NUMBER: 1
val count٤ = 4

fun box(): String = if (count٤ == 4) "OK" else "NOK"
