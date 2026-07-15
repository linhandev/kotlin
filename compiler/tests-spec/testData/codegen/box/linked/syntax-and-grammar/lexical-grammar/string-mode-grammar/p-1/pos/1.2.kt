// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: QUOTE_OPEN empty line string literal
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = ""
    if (s.isNotEmpty()) return "NOK"
    return if (s.length == 0) "OK" else "NOK"
}
