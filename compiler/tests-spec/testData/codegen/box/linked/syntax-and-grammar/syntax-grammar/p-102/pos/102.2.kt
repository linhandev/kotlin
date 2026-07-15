// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 102 -> sentence 102
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 150 -> sentence 150
 * NUMBER: 2
 * DESCRIPTION: navigationSuffix class keyword member access
 */
package syntax.grammar.p102.pos2

// TESTCASE NUMBER: 1
fun box(): String = if (String::class.simpleName == "String") "OK" else "NOK"
