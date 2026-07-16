// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 80 -> sentence 80
 * NUMBER: 5
 * DESCRIPTION: TYPEOF token as backtick-escaped local variable name
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "typeof-local-80"
    val `typeof` = expected
    if (`typeof` != expected) return "NOK"
    return "OK"
}
