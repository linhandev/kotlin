// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 124 -> sentence 124
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 15 -> sentence 15
 * NUMBER: 3
 * DESCRIPTION: objectLiteral data object modifier
 */
package syntax.grammar.p124.pos3

data object Token

// TESTCASE NUMBER: 1
fun box(): String = if (Token.toString().contains("Token")) "OK" else "NOK"
