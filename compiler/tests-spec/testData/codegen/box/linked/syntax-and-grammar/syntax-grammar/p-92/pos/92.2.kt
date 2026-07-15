// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 92 -> sentence 92
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 93 -> sentence 93
 * syntax-and-grammar, syntax-grammar -> paragraph 94 -> sentence 94
 * NUMBER: 2
 * DESCRIPTION: prefixUnaryExpression prefix increment and not operators
 */
package syntax.grammar.p92.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    ++i
    return if (!false && i == 1) "OK" else "NOK"
}
