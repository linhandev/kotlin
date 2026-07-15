// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: LineStrEscapedChar newline escape \n
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "line1\nline2"
    if (s[5] != '\n') return "NOK"
    return if (s == "line1\nline2") "OK" else "NOK"
}
