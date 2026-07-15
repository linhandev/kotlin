// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 90 -> sentence 90
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 91 -> sentence 91
 * syntax-and-grammar, syntax-grammar -> paragraph 145 -> sentence 145
 * NUMBER: 1
 * DESCRIPTION: multiplicativeExpression multiply operator
 */
package syntax.grammar.p90.pos1

// TESTCASE NUMBER: 1
fun box(): String = if (2 * 3 == 6) "OK" else "NOK"
