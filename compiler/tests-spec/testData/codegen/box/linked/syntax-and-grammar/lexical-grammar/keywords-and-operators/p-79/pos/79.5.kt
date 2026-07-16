// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 79 -> sentence 79
 * NUMBER: 5
 * DESCRIPTION: SUPER token as backtick-escaped identifier fun `super`
 */
// TESTCASE NUMBER: 1

fun `super`(): String = "kw-pos-79-5"

fun box(): String {
    val r = `super`()
    if (r.asSequence().count() != r.length) return "NOK"
    return if (r == "kw-pos-79-5") "OK" else "NOK"
}
