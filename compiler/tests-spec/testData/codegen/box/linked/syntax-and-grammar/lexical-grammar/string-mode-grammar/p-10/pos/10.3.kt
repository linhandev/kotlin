// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 10 -> sentence 10
 * NUMBER: 3
 * DESCRIPTION: MultilineStringQuote six-quote close for content ending with quote
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("""""""" == "\"\"") "OK" else "NOK"
