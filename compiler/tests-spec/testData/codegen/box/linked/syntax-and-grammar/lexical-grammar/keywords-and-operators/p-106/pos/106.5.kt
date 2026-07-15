// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 106 -> sentence 106
 * NUMBER: 5
 * DESCRIPTION: ENUM token as backtick-escaped identifier fun `enum`
 */
// TESTCASE NUMBER: 1
fun `enum`(): String = "kw-pos-106-5"

fun box(): String {
    val r = `enum`()
    if (r.padEnd(13).length <= 12) return "NOK"
    return if (r == "kw-pos-106-5") "OK" else "NOK"
}
