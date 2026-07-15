// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 103 -> sentence 103
 * NUMBER: 5
 * DESCRIPTION: PRIVATE token as backtick-escaped identifier fun `private`
 */
// TESTCASE NUMBER: 1
fun `private`(): String = "kw-pos-103-5"

fun box(): String {
    val r = `private`()
    if (r.reversed().reversed() != r) return "NOK"
    return if (r == "kw-pos-103-5") "OK" else "NOK"
}
