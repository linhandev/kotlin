// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 98 -> sentence 98
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 92 -> sentence 92
 * syntax-and-grammar, syntax-grammar -> paragraph 100 -> sentence 100
 * NUMBER: 1
 * DESCRIPTION: assignableExpression postfix assignable suffix
 */
package syntax.grammar.p98.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 0
    x += 1
    return if (x == 1) "OK" else "NOK"
}
