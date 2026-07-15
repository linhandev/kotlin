// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: LineStrText digits and punctuation characters
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "123,./?;"
    if (!s.any { it.isDigit() }) return "NOK"
    if (s.count { it == ',' } != 1) return "NOK"
    return if (s == "123,./?;") "OK" else "NOK"
}
