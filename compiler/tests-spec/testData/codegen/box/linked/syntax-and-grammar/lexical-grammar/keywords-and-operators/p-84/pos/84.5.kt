// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 84 -> sentence 84
 * NUMBER: 5
 * DESCRIPTION: WHEN token as backtick-escaped identifier fun `when`
 */
// TESTCASE NUMBER: 1

fun `when`(): String = "kw-pos-84-5"

fun box(): String {
    val r = `when`()
    if (r.map { it }.size != r.length) return "NOK"
    return if (r == "kw-pos-84-5") "OK" else "NOK"
}
