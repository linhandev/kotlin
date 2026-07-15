// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Ordinary Identifier foo_bar2 with Letter underscore and UnicodeDigit
 */
// TESTCASE NUMBER: 1
val foo_bar2 = 1

fun box(): String = if (foo_bar2.hashCode() == 1.hashCode()) "OK" else "NOK"
