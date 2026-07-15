// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 82 -> sentence 82
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 83 -> sentence 83
 * syntax-and-grammar, syntax-grammar -> paragraph 141 -> sentence 141
 * NUMBER: 1
 * DESCRIPTION: comparison less than operator
 */
package syntax.grammar.p82.pos1

// TESTCASE NUMBER: 1
fun box(): String = if (1 < 2) "OK" else "NOK"
