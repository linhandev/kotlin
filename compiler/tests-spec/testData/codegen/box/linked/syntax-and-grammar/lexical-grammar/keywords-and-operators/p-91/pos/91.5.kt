// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 91 -> sentence 91
 * NUMBER: 5
 * DESCRIPTION: THROW token as backtick-escaped identifier fun `throw`
 */
// TESTCASE NUMBER: 1
fun `throw`(): String = "kw-pos-91-5"

fun box(): String {
    val r = `throw`()
    if (!r.iterator().hasNext()) return "NOK"
    return if (r == "kw-pos-91-5") "OK" else "NOK"
}
