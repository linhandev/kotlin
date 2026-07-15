// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: Escaped Identifier hard keyword `when` as function name
 */
// TESTCASE NUMBER: 1
fun `when`(): String = "kw-pos-4-2"

fun box(): String {
    return if (`when`().startsWith("kw-")) "OK" else "NOK"
}
