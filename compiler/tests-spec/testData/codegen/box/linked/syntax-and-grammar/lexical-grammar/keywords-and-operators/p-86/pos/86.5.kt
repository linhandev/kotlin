// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 86 -> sentence 86
 * NUMBER: 5
 * DESCRIPTION: CATCH token as backtick-escaped identifier fun `catch`
 */
// TESTCASE NUMBER: 1
fun `catch`(): String = "kw-pos-86-5"

fun box(): String {
    val r = `catch`()
    if (r.hashCode() != "kw-pos-86-5".hashCode()) return "NOK"
    return "OK"
}
