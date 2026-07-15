// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: QUOTE_CLOSE empty closed line string
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val empty = ""
    val nonEmpty = "x"
    if (empty.length != 0) return "NOK"
    if (nonEmpty.length == 0) return "NOK"
    return "OK"
}
