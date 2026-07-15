// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 89 -> sentence 89
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 90 -> sentence 90
 * syntax-and-grammar, syntax-grammar -> paragraph 144 -> sentence 144
 * NUMBER: 2
 * DESCRIPTION: additiveExpression minus operator
 */
package syntax.grammar.p89.pos2

// TESTCASE NUMBER: 1
fun box(): String = if (5 - 2 == 3) "OK" else "NOK"
