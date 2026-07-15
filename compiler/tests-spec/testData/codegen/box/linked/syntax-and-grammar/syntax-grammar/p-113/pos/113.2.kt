// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 113 -> sentence 113
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 116 -> sentence 116
 * NUMBER: 2
 * DESCRIPTION: lineStringLiteral line string template expression
 */
package syntax.grammar.p113.pos2

// TESTCASE NUMBER: 1
fun box(): String = if ("v=${1}" == "v=1") "OK" else "NOK"
