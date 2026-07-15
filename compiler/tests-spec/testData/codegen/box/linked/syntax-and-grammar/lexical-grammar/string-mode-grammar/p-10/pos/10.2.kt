// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 10 -> sentence 10
 * NUMBER: 2
 * DESCRIPTION: MultilineStringQuote four-quote value embedded in multiline body
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val quoted = """"m""""
    val text = """
        start
        end: $quoted
        """.trimIndent()
    return if (text.contains("start") && text.contains("\"m\"")) "OK" else "NOK"
}
