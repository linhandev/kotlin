// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 58 -> sentence 58
 * NUMBER: 5
 * DESCRIPTION: PROPERTY token as backtick-escaped identifier fun `property`
 */
// TESTCASE NUMBER: 1

fun `property`(): String = "kw-pos-58-5"

fun box(): String {
    val r = `property`()
    if (r.indices.last != 10) return "NOK"
    return if (r == "kw-pos-58-5") "OK" else "NOK"
}
