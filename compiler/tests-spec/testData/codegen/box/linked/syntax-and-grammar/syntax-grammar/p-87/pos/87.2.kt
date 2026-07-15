// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 87 -> sentence 87
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 88 -> sentence 88
 * syntax-and-grammar, syntax-grammar -> paragraph 174 -> sentence 174
 * NUMBER: 2
 * DESCRIPTION: infixFunctionCall until infix call
 */
package syntax.grammar.p87.pos2

// TESTCASE NUMBER: 1
fun box(): String = if ((0 until 3).count() == 3) "OK" else "NOK"
