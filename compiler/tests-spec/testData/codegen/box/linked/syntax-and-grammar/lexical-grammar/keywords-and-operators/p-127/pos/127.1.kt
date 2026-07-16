// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 127 -> sentence 127
 * NUMBER: 1
 * DESCRIPTION: EXPECT token used as backtick-escaped top-level function name
 */
// TESTCASE NUMBER: 1
fun `expect`(): String = "kw-pos-127-1"

fun box(): String {
    val r = `expect`()
    if (r.chunked(1).size != r.length) return "NOK"
    return if (r == "kw-pos-127-1") "OK" else "NOK"
}
