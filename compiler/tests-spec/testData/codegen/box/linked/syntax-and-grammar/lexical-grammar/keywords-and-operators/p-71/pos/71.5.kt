// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 71 -> sentence 71
 * NUMBER: 5
 * DESCRIPTION: VAL token as backtick-escaped identifier fun `val`
 */
// TESTCASE NUMBER: 1

fun `val`(): String = "kw-pos-71-5"

fun box(): String {
    val r = `val`(); var n = 0; for (ch in r) { n++ }; if (n == 0) return "NOK"; return if (r == "kw-pos-71-5") "OK" else "NOK"
}
