// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 2 -> sentence 2
 * NUMBER: 5
 * DESCRIPTION: QuotedSymbol mixed alphanumeric `a b c 123`
 */
// TESTCASE NUMBER: 1
val `a b c 123` = 5

fun box(): String = if (`a b c 123` == 5) "OK" else "NOK"
