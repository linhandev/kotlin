// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 9 -> sentence 9
 * NUMBER: 4
 * DESCRIPTION: TRIPLE_QUOTE_CLOSE returned from function
 */
// TESTCASE NUMBER: 1
fun content(): String = """done"""

fun box(): String { val ok = content() == "done"; return when { ok -> "OK"; else -> "NOK" } }
