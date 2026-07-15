// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 83 -> sentence 83
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 84 -> sentence 84
 * syntax-and-grammar, syntax-grammar -> paragraph 103 -> sentence 103
 * syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * NUMBER: 1
 * DESCRIPTION: genericCallLikeComparison call with type arguments
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p83.pos1

fun <T> id(v: T): T = v

fun box(): String = if (id<Int>(1) == 1) "OK" else "NOK"
