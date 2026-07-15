// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 119 -> sentence 119
 * NUMBER: 5
 * DESCRIPTION: FINAL token as backtick-escaped identifier fun `final`
 */
// TESTCASE NUMBER: 1
fun `final`(): String = "kw-pos-119-5"

fun box(): String {
    val r = `final`()
    if (r.lastOrNull() == null) return "NOK"
    return if (r == "kw-pos-119-5") "OK" else "NOK"
}
