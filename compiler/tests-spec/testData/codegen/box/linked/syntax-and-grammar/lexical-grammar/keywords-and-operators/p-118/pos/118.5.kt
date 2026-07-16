// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 118 -> sentence 118
 * NUMBER: 5
 * DESCRIPTION: ABSTRACT token as backtick-escaped identifier fun `abstract`
 */
// TESTCASE NUMBER: 1
fun `abstract`(): String = "kw-pos-118-5"

fun box(): String {
    val r = `abstract`()
    if (r.sumOf { it.code.toLong() } <= 0) return "NOK"
    return if (r == "kw-pos-118-5") "OK" else "NOK"
}
