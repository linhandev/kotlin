// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 115 -> sentence 115
 * NUMBER: 5
 * DESCRIPTION: EXTERNAL token as backtick-escaped identifier fun `external`
 */
// TESTCASE NUMBER: 1
fun `external`(): String = "kw-pos-115-5"

fun box(): String {
    val r = `external`()
    if (r.substringBefore('-').isEmpty()) return "NOK"
    return if (r == "kw-pos-115-5") "OK" else "NOK"
}
