// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 113 -> sentence 113
 * NUMBER: 5
 * DESCRIPTION: INLINE token as backtick-escaped identifier fun `inline`
 */
// TESTCASE NUMBER: 1
fun `inline`(): String = "kw-pos-113-5"

fun box(): String {
    val r = `inline`()
    if (r.withIndex().count() != 12) return "NOK"
    return if (r == "kw-pos-113-5") "OK" else "NOK"
}
