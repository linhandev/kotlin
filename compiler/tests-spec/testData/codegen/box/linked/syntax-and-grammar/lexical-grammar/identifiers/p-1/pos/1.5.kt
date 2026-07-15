// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: Letter underscore as Identifier start with following Letter
 */
// TESTCASE NUMBER: 1
val _hidden = 5

fun box(): String = if (_hidden == 5) "OK" else "NOK"
