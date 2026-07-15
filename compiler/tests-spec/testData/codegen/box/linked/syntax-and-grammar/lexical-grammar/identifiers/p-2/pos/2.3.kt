// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: QuotedSymbol hard keyword if as backtick identifier
 */
// TESTCASE NUMBER: 1
fun `if`(): String = "kw-pos-2-3"

fun box(): String {
    val expected = "kw-pos-2-3"
    return if (`if`().length == expected.length && `if`() == expected) "OK" else "NOK"
}
