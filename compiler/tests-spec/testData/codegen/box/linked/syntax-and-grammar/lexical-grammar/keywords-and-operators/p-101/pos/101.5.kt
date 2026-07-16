// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 101 -> sentence 101
 * NUMBER: 5
 * DESCRIPTION: DYNAMIC token as backtick-escaped identifier fun `dynamic`
 */
// TESTCASE NUMBER: 1
fun `dynamic`(): String = "kw-pos-101-5"

fun box(): String {
    val r = `dynamic`()
    val h = r.hashCode()
    if ("kw-pos-101-5".hashCode() != h) return "NOK"
    return "OK"
}
