// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 126 -> sentence 126
 * NUMBER: 5
 * DESCRIPTION: REIFIED token as backtick-escaped identifier fun `reified`
 */
// TESTCASE NUMBER: 1
fun `reified`(): String = "kw-pos-126-5"

fun box(): String {
    val r = `reified`()
    if (r.filter { it != '-' }.length < 1) return "NOK"
    return if (r == "kw-pos-126-5") "OK" else "NOK"
}
