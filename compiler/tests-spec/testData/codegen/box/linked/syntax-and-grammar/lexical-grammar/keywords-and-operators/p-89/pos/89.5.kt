// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 89 -> sentence 89
 * NUMBER: 5
 * DESCRIPTION: DO token as backtick-escaped identifier fun `do`
 */
// TESTCASE NUMBER: 1
fun `do`(): String = "kw-pos-89-5"

fun box(): String {
    val r = run { `do`() }
    return if (r == "kw-pos-89-5") "OK" else "NOK"
}
