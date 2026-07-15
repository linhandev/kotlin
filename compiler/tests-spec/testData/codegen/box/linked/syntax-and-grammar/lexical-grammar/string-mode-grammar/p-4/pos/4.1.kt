// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: QUOTE_CLOSE properly closed line string text
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "closed"
    if (s.isBlank()) return "NOK"
    return if (s == "closed") "OK" else "NOK"
}
