// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: TRIPLE_QUOTE_OPEN multiline string with leading newline trimmed
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = """
abc""".trimIndent()
    return if (s == "abc") "OK" else "NOK"
}
