// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 83 -> sentence 83
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 84 -> sentence 84
 * NUMBER: 2
 * DESCRIPTION: genericCallLikeComparison infixOperation without callSuffix; infix call evaluates correctly
 */
package syntax.grammar.p83.pos2

infix fun Int.add83(other: Int): Int = this + other

// TESTCASE NUMBER: 1
fun box(): String = if ((11 add83 22) == 33) "OK" else "NOK"
