// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 5 -> sentence 5
 * NUMBER: 4
 * DESCRIPTION: LineStrRef at end of line string
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val tail = "end"
    val s = "prefix $tail"
    if (!s.endsWith("end")) return "NOK"
    return if (s == "prefix end") "OK" else "NOK"
}
