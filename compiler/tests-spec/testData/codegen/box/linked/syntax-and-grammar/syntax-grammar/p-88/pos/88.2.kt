// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 88 -> sentence 88
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 89 -> sentence 89
 * NUMBER: 2
 * DESCRIPTION: rangeExpression half-open range with dot dot less
 */
package syntax.grammar.p88.pos2

// TESTCASE NUMBER: 1
fun box(): String = if ((1..<4).count() == 3) "OK" else "NOK"
