// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: QuotedSymbol special characters and space in `#@$ a b`
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val `#@$ a b` = 1
    return if (`#@$ a b` == 1) "OK" else "NOK"
}
