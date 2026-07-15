// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 125 -> sentence 125
 * NUMBER: 5
 * DESCRIPTION: CROSSINLINE token as backtick-escaped identifier fun `crossinline`
 */
// TESTCASE NUMBER: 1
fun `crossinline`(): String = "kw-pos-125-5"

fun box(): String {
    val r = `crossinline`()
    if (r.any { it == r.first() }.not()) return "NOK"
    return if (r == "kw-pos-125-5") "OK" else "NOK"
}
