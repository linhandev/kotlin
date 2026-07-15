// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 107 -> sentence 107
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: valueArgument named argument
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p107.pos1

fun pair(first: Int, second: Int): Int = first + second

fun box(): String = if (pair(first = 1, second = 2) == 3) "OK" else "NOK"
