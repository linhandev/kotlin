// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 88 -> sentence 88
 * NUMBER: 5
 * DESCRIPTION: FOR token as backtick-escaped identifier fun `for`
 */
// TESTCASE NUMBER: 1
fun `for`(): String = "kw-pos-88-5"

fun box(): String {
    val r = `for`()
    var n = 0
    for (ch in r) { n++ }
    if (n == 0) return "NOK"
    return if (r == "kw-pos-88-5") "OK" else "NOK"
}
