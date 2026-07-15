// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 76 -> sentence 76
 * NUMBER: 5
 * DESCRIPTION: COMPANION token as backtick-escaped identifier fun `companion`
 */
// TESTCASE NUMBER: 1

fun `companion`(): String = "kw-pos-76-5"

fun box(): String {
    val r = `companion`(); if (r.sumOf { it.code.toLong() } <= 0) return "NOK"; return if (r == "kw-pos-76-5") "OK" else "NOK"
}
