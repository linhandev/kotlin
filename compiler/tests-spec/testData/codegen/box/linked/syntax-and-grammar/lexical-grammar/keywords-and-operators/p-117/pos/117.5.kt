// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 117 -> sentence 117
 * NUMBER: 5
 * DESCRIPTION: OVERRIDE token as backtick-escaped identifier fun `override`
 */
// TESTCASE NUMBER: 1
fun `override`(): String = "kw-pos-117-5"

fun box(): String {
    val r = `override`()
    if (r.length < 12) return "NOK"
    if (r.length > 12) return "NOK"
    return if (r == "kw-pos-117-5") "OK" else "NOK"
}
