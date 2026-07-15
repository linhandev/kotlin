// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 78 -> sentence 78
 * NUMBER: 5
 * DESCRIPTION: THIS token as backtick-escaped identifier fun `this`
 */
// TESTCASE NUMBER: 1

fun `this`(): String = "kw-pos-78-5"

fun box(): String {
    val r = `this`()
    if (!r.matches(Regex("kw-pos-78-5"))) return "NOK"
    return "OK"
}
