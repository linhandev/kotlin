// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 109 -> sentence 109
 * NUMBER: 5
 * DESCRIPTION: DATA token as backtick-escaped identifier fun `data`
 */
// TESTCASE NUMBER: 1
fun `data`(): String = "kw-pos-109-5"

fun box(): String {
    val r = `data`()
    if (r.replace('-', '-').length != 12) return "NOK"
    return if (r == "kw-pos-109-5") "OK" else "NOK"
}
