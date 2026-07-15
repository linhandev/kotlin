// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 90 -> sentence 90
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 91 -> sentence 91
 * syntax-and-grammar, syntax-grammar -> paragraph 145 -> sentence 145
 * NUMBER: 2
 * DESCRIPTION: multiplicativeExpression divide and remainder operators
 */
package syntax.grammar.p90.pos2

// TESTCASE NUMBER: 1
fun box(): String = if (6 / 2 == 3 && 7 % 3 == 1) "OK" else "NOK"
