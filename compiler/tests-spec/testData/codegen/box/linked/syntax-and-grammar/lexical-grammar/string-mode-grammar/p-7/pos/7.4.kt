// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 7 -> sentence 7
 * NUMBER: 4
 * DESCRIPTION: LineStrEscapedChar backslash and dollar escapes
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "back\\dollar"
    if (s.length < 3) return "NOK"
    return if (s.contains("\\")) "OK" else "NOK"
}
