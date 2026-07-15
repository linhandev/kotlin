// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 5 -> sentence 5
 * NUMBER: 5
 * DESCRIPTION: Hard keyword if escaped as Identifier `if`
 */
// TESTCASE NUMBER: 1
val `if` = 5

fun box(): String = if (`if` == 5) "OK" else "NOK"
