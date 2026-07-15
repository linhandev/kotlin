// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 96 -> sentence 96
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 1
 * DESCRIPTION: directlyAssignableExpression simple identifier
 */
package syntax.grammar.p96.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 0
    x = 1
    return if (x == 1) "OK" else "NOK"
}
