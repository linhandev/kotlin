// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 75 -> sentence 75
 * NUMBER: 5
 * DESCRIPTION: BY token as backtick-escaped identifier fun `by`
 */
// TESTCASE NUMBER: 1

fun `by`(): String = "kw-pos-75-5"

fun box(): String {
    val r = `by`(); if (r.fold(0) { acc, _ -> acc + 1 } != r.length) return "NOK"; return if (r == "kw-pos-75-5") "OK" else "NOK"
}
