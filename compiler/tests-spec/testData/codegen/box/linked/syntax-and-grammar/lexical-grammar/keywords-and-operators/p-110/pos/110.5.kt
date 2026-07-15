// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 110 -> sentence 110
 * NUMBER: 5
 * DESCRIPTION: INNER token as backtick-escaped identifier fun `inner`
 */
// TESTCASE NUMBER: 1
fun `inner`(): String = "kw-pos-110-5"

fun box(): String {
    var ok = false
    val r = `inner`()
    if (r == "kw-pos-110-5") ok = true
    return if (ok) "OK" else "NOK"
}
