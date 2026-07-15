// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 81 -> sentence 81
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 82 -> sentence 82
 * syntax-and-grammar, syntax-grammar -> paragraph 140 -> sentence 140
 * NUMBER: 1
 * DESCRIPTION: equality double equals comparison
 */
package syntax.grammar.p81.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1
    val b = 1
    if (a == b) return "OK"
    return "NOK"
}
