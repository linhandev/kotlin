// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 109 -> sentence 109
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: parenthesizedExpression grouped expression
 */
package syntax.grammar.p109.pos1

// TESTCASE NUMBER: 1
fun box(): String = if ((1 + 2) == 3) "OK" else "NOK"
