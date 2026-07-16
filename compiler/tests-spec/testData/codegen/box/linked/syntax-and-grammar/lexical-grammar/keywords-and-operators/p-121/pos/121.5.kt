// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 121 -> sentence 121
 * NUMBER: 5
 * DESCRIPTION: CONST token as backtick-escaped identifier fun `const`
 */
// TESTCASE NUMBER: 1
fun `const`(): String = "kw-pos-121-5"

fun box(): String {
    val r = `const`()
    if (r.none { false }) { /* ok */ } else return "NOK"
    return if (r == "kw-pos-121-5") "OK" else "NOK"
}
