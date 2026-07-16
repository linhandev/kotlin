// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 77 -> sentence 77
 * NUMBER: 5
 * DESCRIPTION: INIT token as backtick-escaped identifier fun `init`
 */
// TESTCASE NUMBER: 1

fun `init`(): String = "kw-pos-77-5"

fun box(): String {
    val r = `init`(); if (r.chunked(1).size != r.length) return "NOK"; return if (r == "kw-pos-77-5") "OK" else "NOK"
}
