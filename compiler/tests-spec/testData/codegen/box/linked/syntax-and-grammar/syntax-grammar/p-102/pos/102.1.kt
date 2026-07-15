// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 102 -> sentence 102
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 150 -> sentence 150
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 1
 * DESCRIPTION: navigationSuffix dot member access
 */
package syntax.grammar.p102.pos1

// TESTCASE NUMBER: 1
fun box(): String { val ok = "ab".length == 2; return ok.takeIf { it }?.let { "OK" } ?: "NOK" }
