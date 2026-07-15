// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 2
 * DESCRIPTION: expression function call via disjunction
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p78.pos2

fun id(v: Int): Int = v

fun box(): String { val ok = id(42) == 42; return ok.takeIf { it }?.let { "OK" } ?: "NOK" }
