// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 94 -> sentence 94
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 108 -> sentence 108
 * syntax-and-grammar, syntax-grammar -> paragraph 95 -> sentence 95
 * NUMBER: 1
 * DESCRIPTION: postfixUnaryExpression postfix decrement operator
 */
package syntax.grammar.p94.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 1
    i--
    return if (i == 0) "OK" else "NOK"
}
