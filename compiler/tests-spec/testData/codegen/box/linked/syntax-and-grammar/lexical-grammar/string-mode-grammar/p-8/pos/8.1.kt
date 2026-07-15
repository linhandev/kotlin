// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: LineStrExprStart ${1 + 2} expression
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("${1 + 2}" == "3") "OK" else "NOK"
