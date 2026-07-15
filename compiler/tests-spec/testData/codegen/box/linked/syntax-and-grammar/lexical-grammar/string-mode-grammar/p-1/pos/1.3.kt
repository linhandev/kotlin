// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: QUOTE_OPEN line string with digits and symbols
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "a1!@#"
    if (!s.any { it.isDigit() }) return "NOK"
    if (!s.any { !it.isLetterOrDigit() }) return "NOK"
    return if (s == "a1!@#") "OK" else "NOK"
}
