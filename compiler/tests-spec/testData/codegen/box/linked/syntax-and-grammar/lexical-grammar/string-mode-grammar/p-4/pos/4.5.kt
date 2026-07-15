// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 4 -> sentence 4
 * NUMBER: 5
 * DESCRIPTION: QUOTE_CLOSE string with escaped internal quote
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "say \"hi\""
    if (s.length != 8) return "NOK"
    if (s != "say \"hi\"") return "NOK"
    return "OK"
}
