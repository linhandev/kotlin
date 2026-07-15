// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 84 -> sentence 84
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 85 -> sentence 85
 * syntax-and-grammar, syntax-grammar -> paragraph 142 -> sentence 142
 * NUMBER: 1
 * DESCRIPTION: infixOperation in range check
 */
package syntax.grammar.p84.pos1

// TESTCASE NUMBER: 1
fun box(): String = if (2 in 1..3) "OK" else "NOK"
