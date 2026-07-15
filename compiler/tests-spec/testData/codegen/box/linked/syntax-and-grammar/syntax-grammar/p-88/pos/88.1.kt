// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 88 -> sentence 88
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 89 -> sentence 89
 * NUMBER: 1
 * DESCRIPTION: rangeExpression closed range with dot dot
 */
package syntax.grammar.p88.pos1

// TESTCASE NUMBER: 1
fun box(): String = if ((1..3).count() == 3) "OK" else "NOK"
