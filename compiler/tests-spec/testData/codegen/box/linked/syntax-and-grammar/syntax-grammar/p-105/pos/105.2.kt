// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 57 -> sentence 57
 * NUMBER: 2
 * DESCRIPTION: typeArguments multiple type projections
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p105.pos2

fun <K, V> pair(k: K, v: V): Pair<K, V> = k to v

fun box(): String = if (pair<String, Int>("a", 1).second == 1) "OK" else "NOK"
