// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: LineStrText plain alphabetic text abc def
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "abc def"
    if (s.length != 7) return "NOK"
    if (s[0] != 'a' || s[6] != 'f') return "NOK"
    return if (s == "abc def") "OK" else "NOK"
}
