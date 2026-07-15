// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 101 -> sentence 101
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: indexingSuffix array access
 */
package syntax.grammar.p101.pos1

// TESTCASE NUMBER: 1
fun box(): String = if (intArrayOf(1, 2)[1] == 2) "OK" else "NOK"
