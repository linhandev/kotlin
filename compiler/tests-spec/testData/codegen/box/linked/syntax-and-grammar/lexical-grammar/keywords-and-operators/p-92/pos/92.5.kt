// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 92 -> sentence 92
 * NUMBER: 5
 * DESCRIPTION: RETURN token as backtick-escaped identifier fun `return`
 */
// TESTCASE NUMBER: 1
fun `return`(): String = "kw-pos-92-5"

fun box(): String {
    val r = `return`()
    if (r.drop(1).length != r.length - 1) return "NOK"
    return if (r == "kw-pos-92-5") "OK" else "NOK"
}
