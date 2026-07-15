// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 84 -> sentence 84
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 85 -> sentence 85
 * NUMBER: 2
 * DESCRIPTION: infixOperation plain elvisExpression without in or is
 */
package syntax.grammar.p84.pos2

// TESTCASE NUMBER: 1
fun nullableLength(s: String?): Int = s?.length ?: 0

fun box(): String {
    if (nullableLength(null) != 0) return "NOK"
    if (nullableLength("ab") != 2) return "NOK"
    return "OK"
}
