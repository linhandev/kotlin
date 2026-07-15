// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 81 -> sentence 81
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 82 -> sentence 82
 * syntax-and-grammar, syntax-grammar -> paragraph 140 -> sentence 140
 * NUMBER: 2
 * DESCRIPTION: equality not equals comparison
 */
package syntax.grammar.p81.pos2

// TESTCASE NUMBER: 1
fun box(): String = if (1 != 2) "OK" else "NOK"
