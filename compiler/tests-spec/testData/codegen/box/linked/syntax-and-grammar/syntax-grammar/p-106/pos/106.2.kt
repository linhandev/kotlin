// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 106 -> sentence 106
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 107 -> sentence 107
 * NUMBER: 2
 * DESCRIPTION: valueArguments empty argument list
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p106.pos2

fun noop(): Int = 1

fun box(): String = when { noop() == 1 -> "OK"; else -> "NOK" }
