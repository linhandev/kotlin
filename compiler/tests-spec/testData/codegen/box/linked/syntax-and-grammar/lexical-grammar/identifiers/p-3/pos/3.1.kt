// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: UnicodeDigit Devanagari १ in identifier a१
 */
// TESTCASE NUMBER: 1
val a१ = 1

fun box(): String = if (a१ == 1) "OK" else "NOK"
