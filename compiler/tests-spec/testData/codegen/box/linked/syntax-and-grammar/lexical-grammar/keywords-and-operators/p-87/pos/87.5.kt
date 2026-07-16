// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 87 -> sentence 87
 * NUMBER: 5
 * DESCRIPTION: FINALLY token as backtick-escaped identifier fun `finally`
 */
// TESTCASE NUMBER: 1
fun `finally`(): String = "kw-pos-87-5"

fun box(): String {
    val r = `finally`()
    if (r.padEnd(r.length + 1).length <= r.length) return "NOK"
    return if (r == "kw-pos-87-5") "OK" else "NOK"
}
