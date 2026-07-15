// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 2 -> sentence 2
 * NUMBER: 5
 * DESCRIPTION: TRIPLE_QUOTE_OPEN multiline string concatenation
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("""a""" + """b""" == "ab") "OK" else "NOK"
