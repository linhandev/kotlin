// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: QuotedSymbol digits in backtick identifier `123`
 */
// TESTCASE NUMBER: 1
val `123` = 123

fun box(): String = if (`123` == 123) "OK" else "NOK"
