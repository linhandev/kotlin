// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 4 -> sentence 4
 * NUMBER: 5
 * DESCRIPTION: Escaped Identifier `a-b-c` with non-alphanumeric QuotedSymbol
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val `a-b-c` = 5
    return if (`a-b-c` == 5) "OK" else "NOK"
}
