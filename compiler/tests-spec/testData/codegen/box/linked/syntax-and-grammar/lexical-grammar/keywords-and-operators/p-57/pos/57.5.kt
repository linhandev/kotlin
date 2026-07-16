// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 57 -> sentence 57
 * NUMBER: 5
 * DESCRIPTION: FIELD token as backtick-escaped identifier fun `field`
 */
// TESTCASE NUMBER: 1

fun `field`(): String = "kw-pos-57-5"

fun box(): String {
    val r = `field`()
    if (r.endsWith("kw-pos-57-5".takeLast(2)).not()) return "NOK"
    return if (r == "kw-pos-57-5") "OK" else "NOK"
}
