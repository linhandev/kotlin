// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: TRIPLE_QUOTE_OPEN single-line multiline string
 */
// TESTCASE NUMBER: 1
fun box(): String { val ok = """text""" == "text"; if (ok) { return "OK" } else { return "NOK" } }
