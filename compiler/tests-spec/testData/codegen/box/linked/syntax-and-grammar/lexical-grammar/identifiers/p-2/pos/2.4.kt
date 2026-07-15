// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: QuotedSymbol operator-like sequence in `(special)`
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val `(special)` = 4
    return if (`(special)` == 4) "OK" else "NOK"
}
