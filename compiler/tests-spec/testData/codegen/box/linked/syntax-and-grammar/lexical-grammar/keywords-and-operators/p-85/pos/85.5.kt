// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 85 -> sentence 85
 * NUMBER: 5
 * DESCRIPTION: TRY token as backtick-escaped identifier fun `try`
 */
// TESTCASE NUMBER: 1
fun `try`(): String = "kw-pos-85-5"

fun box(): String {
    val r = `try`()
    if (r.lastIndexOf('-') < 0) return "NOK"
    return if (r == "kw-pos-85-5") "OK" else "NOK"
}
