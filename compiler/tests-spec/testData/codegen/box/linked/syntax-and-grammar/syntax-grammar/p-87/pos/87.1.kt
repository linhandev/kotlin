// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 87 -> sentence 87
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 88 -> sentence 88
 * syntax-and-grammar, syntax-grammar -> paragraph 174 -> sentence 174
 * NUMBER: 1
 * DESCRIPTION: infixFunctionCall shl operator
 */
package syntax.grammar.p87.pos1

// TESTCASE NUMBER: 1
fun box(): String { if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; return if (1 shl 2 == 4) "OK" else "NOK" }
