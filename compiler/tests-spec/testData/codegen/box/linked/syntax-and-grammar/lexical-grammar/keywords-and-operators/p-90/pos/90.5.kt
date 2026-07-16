// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 90 -> sentence 90
 * NUMBER: 5
 * DESCRIPTION: WHILE token as backtick-escaped identifier fun `while`
 */
// TESTCASE NUMBER: 1
fun `while`(): String = "kw-pos-90-5"

fun box(): String {
    val r = `while`()
    val t = r.takeIf { it == "kw-pos-90-5" }
    return if (t != null) "OK" else "NOK"
}
