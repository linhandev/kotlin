// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 113 -> sentence 113
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 115 -> sentence 115
 * NUMBER: 1
 * DESCRIPTION: lineStringLiteral escaped quotes
 */
package syntax.grammar.p113.pos1

// TESTCASE NUMBER: 1
fun box(): String = if ("a\nb".contains("\n")) "OK" else "NOK"
