// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 82 -> sentence 82
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 83 -> sentence 83
 * syntax-and-grammar, syntax-grammar -> paragraph 141 -> sentence 141
 * NUMBER: 2
 * DESCRIPTION: comparison greater than and less or equal operators
 */
package syntax.grammar.p82.pos2

// TESTCASE NUMBER: 1
fun box(): String = if (5 > 3 && 2 <= 2) "OK" else "NOK"
