// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 111 -> sentence 111
 * NUMBER: 5
 * DESCRIPTION: TAILREC token as backtick-escaped identifier fun `tailrec`
 */
// TESTCASE NUMBER: 1
fun `tailrec`(): String = "kw-pos-111-5"

fun box(): String {
    val r = `tailrec`()
    if (r != "kw-pos-111-5") return "NOK"
    return "OK"
}
