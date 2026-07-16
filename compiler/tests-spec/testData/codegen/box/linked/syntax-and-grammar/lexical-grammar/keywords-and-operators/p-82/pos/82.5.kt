// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 82 -> sentence 82
 * NUMBER: 5
 * DESCRIPTION: IF token as backtick-escaped identifier fun `if`
 */
// TESTCASE NUMBER: 1

fun `if`(): String = "kw-pos-82-5"

fun box(): String {
    val r = `if`()
    if (r.toMutableList().size != r.length) return "NOK"
    return if (r == "kw-pos-82-5") "OK" else "NOK"
}
