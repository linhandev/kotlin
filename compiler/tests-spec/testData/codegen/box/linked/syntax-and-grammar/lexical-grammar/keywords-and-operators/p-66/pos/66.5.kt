// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 66 -> sentence 66
 * NUMBER: 5
 * DESCRIPTION: IMPORT token as backtick-escaped identifier fun `import`
 */
// TESTCASE NUMBER: 1

fun `import`(): String = "kw-pos-66-5"

fun box(): String {
    val r = `import`()
    if (r.codePointCount(0, r.length) != r.length) return "NOK"
    return if (r == "kw-pos-66-5") "OK" else "NOK"
}
