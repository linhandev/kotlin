// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 80 -> sentence 80
 * NUMBER: 2
 * DESCRIPTION: TYPEOF token as backtick-escaped class name
 */
// TESTCASE NUMBER: 1

fun `typeof`(): String = "typeof-80-2"

fun box(): String {
    val r = `typeof`()
    if (r.zip(r).count() != r.length) return "NOK"
    return if (r == "typeof-80-2") "OK" else "NOK"
}
