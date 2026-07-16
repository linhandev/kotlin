// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 114 -> sentence 114
 * NUMBER: 5
 * DESCRIPTION: INFIX token as backtick-escaped identifier fun `infix`
 */
// TESTCASE NUMBER: 1
fun `infix`(): String = "kw-pos-114-5"

fun box(): String {
    val r = `infix`()
    if (r.drop(1).length != 11) return "NOK"
    return if (r == "kw-pos-114-5") "OK" else "NOK"
}
