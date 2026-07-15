// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 175 -> sentence 175
 * NUMBER: 2
 * DESCRIPTION: identifier three-part qualified chain in type reference
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p175.pos2

val xs: kotlin.collections.List<Int> = listOf(1)

fun box(): String = if (xs.size == 1) "OK" else "NOK"
