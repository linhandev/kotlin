// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 9 -> sentence 9
 * NUMBER: 2
 * DESCRIPTION: TRIPLE_QUOTE_CLOSE multiline string with newline content
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("""a
b""" == "a\nb") "OK" else "NOK"
