// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 3 -> sentence 3
 * NUMBER: 5
 * DESCRIPTION: UnicodeDigit in backtick identifier `num१`
 */
// TESTCASE NUMBER: 1
val `num१` = 5

fun box(): String = if (`num१` == 5) "OK" else "NOK"
