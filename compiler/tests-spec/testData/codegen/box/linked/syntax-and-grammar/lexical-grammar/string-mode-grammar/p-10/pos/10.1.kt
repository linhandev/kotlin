// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: MultilineStringQuote content is single double-quote """"""
 */
// TESTCASE NUMBER: 1
fun box(): String = if (""""""" == "\"") "OK" else "NOK"
