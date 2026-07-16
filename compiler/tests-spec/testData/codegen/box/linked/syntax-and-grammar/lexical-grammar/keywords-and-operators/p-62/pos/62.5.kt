// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 62 -> sentence 62
 * NUMBER: 5
 * DESCRIPTION: PARAM token as backtick-escaped identifier fun `param`
 */
// TESTCASE NUMBER: 1

fun `param`(): String = "kw-pos-62-5"

fun box(): String {
    val r = `param`()
    val b = StringBuilder(r)
    if (b.length != 11) return "NOK"
    return if (r == "kw-pos-62-5") "OK" else "NOK"
}
