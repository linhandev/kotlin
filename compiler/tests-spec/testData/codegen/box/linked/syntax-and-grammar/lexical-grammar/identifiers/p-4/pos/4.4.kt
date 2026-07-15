// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 4 -> sentence 4
 * NUMBER: 4
 * DESCRIPTION: Escaped Identifier `a b` with space in QuotedSymbol sequence
 */
// TESTCASE NUMBER: 1
val `a b` = 4

fun box(): String = if (`a b` == 4) "OK" else "NOK"
