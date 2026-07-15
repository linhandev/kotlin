// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 102 -> sentence 102
 * NUMBER: 5
 * DESCRIPTION: PUBLIC token as backtick-escaped identifier fun `public`
 */
// TESTCASE NUMBER: 1
fun `public`(): String = "kw-pos-102-5"

fun box(): String {
    val r = `public`().let { it }
    return if (r == "kw-pos-102-5") "OK" else "NOK"
}
