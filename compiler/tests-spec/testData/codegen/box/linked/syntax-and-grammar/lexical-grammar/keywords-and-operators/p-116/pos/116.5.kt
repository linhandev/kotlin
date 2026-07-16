// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 116 -> sentence 116
 * NUMBER: 5
 * DESCRIPTION: SUSPEND token as backtick-escaped identifier fun `suspend`
 */
// TESTCASE NUMBER: 1
fun `suspend`(): String = "kw-pos-116-5"

fun box(): String {
    val r = `suspend`()
    if (r.fold(0) { acc, _ -> acc + 1 } != r.length) return "NOK"
    return if (r == "kw-pos-116-5") "OK" else "NOK"
}
