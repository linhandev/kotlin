// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 74 -> sentence 74
 * NUMBER: 5
 * DESCRIPTION: CONSTRUCTOR token as backtick-escaped identifier fun `constructor`
 */
// TESTCASE NUMBER: 1

fun `constructor`(): String = "kw-pos-74-5"

fun box(): String {
    val r = `constructor`().let { it }; return if (r == "kw-pos-74-5") "OK" else "NOK"
}
