// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 93 -> sentence 93
 * NUMBER: 5
 * DESCRIPTION: CONTINUE token as backtick-escaped identifier fun `continue`
 */
// TESTCASE NUMBER: 1
fun `continue`(): String = "kw-pos-93-5"

fun box(): String {
    val r = `continue`()
    if (r.indices.last != r.length - 1) return "NOK"
    return if (r == "kw-pos-93-5") "OK" else "NOK"
}
