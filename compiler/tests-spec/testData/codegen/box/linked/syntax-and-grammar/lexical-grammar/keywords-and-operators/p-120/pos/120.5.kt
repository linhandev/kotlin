// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 120 -> sentence 120
 * NUMBER: 5
 * DESCRIPTION: OPEN token as backtick-escaped identifier fun `open`
 */
// TESTCASE NUMBER: 1
fun `open`(): String = "kw-pos-120-5"

fun box(): String {
    val r = `open`()
    if (r.toCharArray().isEmpty()) return "NOK"
    return if (r == "kw-pos-120-5") "OK" else "NOK"
}
