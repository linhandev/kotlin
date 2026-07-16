// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 72 -> sentence 72
 * NUMBER: 5
 * DESCRIPTION: VAR token as backtick-escaped identifier fun `var`
 */
// TESTCASE NUMBER: 1

fun `var`(): String = "kw-pos-72-5"

fun box(): String {
    val r = run { `var`() }; return if (r == "kw-pos-72-5") "OK" else "NOK"
}
