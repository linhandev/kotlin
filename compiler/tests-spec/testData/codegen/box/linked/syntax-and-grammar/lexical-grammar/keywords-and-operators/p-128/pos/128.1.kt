// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 128 -> sentence 128
 * NUMBER: 1
 * DESCRIPTION: ACTUAL token used as backtick-escaped top-level function name
 */
// TESTCASE NUMBER: 1
fun `actual`(): String = "kw-pos-128-1"

fun box(): String {
    val r = `actual`()
    if (r.uppercase().length != 12) return "NOK"
    return if (r == "kw-pos-128-1") "OK" else "NOK"
}
