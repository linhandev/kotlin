// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 112 -> sentence 112
 * NUMBER: 5
 * DESCRIPTION: OPERATOR token as backtick-escaped identifier fun `operator`
 */
// TESTCASE NUMBER: 1
fun `operator`(): String = "kw-pos-112-5"

fun box(): String {
    val r = `operator`()
    if (!r.isNotEmpty()) return "NOK"
    return if (r == "kw-pos-112-5") "OK" else "NOK"
}
