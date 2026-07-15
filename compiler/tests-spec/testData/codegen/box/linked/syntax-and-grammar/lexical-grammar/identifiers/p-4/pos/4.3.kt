// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 4 -> sentence 4
 * NUMBER: 3
 * DESCRIPTION: Escaped and non-escaped Identifier foo and `foo` refer to same entity
 */
// TESTCASE NUMBER: 1
val foo = 3

fun box(): String = if (`foo` == foo) "OK" else "NOK"
