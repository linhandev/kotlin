// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 103 -> sentence 103
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * syntax-and-grammar, syntax-grammar -> paragraph 106 -> sentence 106
 * syntax-and-grammar, syntax-grammar -> paragraph 104 -> sentence 104
 * NUMBER: 1
 * DESCRIPTION: callSuffix function invocation
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p103.pos1

fun twice(n: Int): Int = n * 2

fun box(): String { return if (!(twice(2) == 4)) "NOK" else "OK" }
