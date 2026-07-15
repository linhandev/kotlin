// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: MultiLineStrExprStart ${1 + 2} in multiline string
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("""sum=${1 + 2}""" == "sum=3") "OK" else "NOK"
