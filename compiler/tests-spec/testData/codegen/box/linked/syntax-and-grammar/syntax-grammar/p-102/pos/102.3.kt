// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 102 -> sentence 102
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 150 -> sentence 150
 * syntax-and-grammar, syntax-grammar -> paragraph 151 -> sentence 151
 * NUMBER: 3
 * DESCRIPTION: navigationSuffix safe call operator
 */
package syntax.grammar.p102.pos3

// TESTCASE NUMBER: 1
fun box(): String {
    val s: String? = "ab"
    return if (s?.length == 2) "OK" else "NOK"
}
