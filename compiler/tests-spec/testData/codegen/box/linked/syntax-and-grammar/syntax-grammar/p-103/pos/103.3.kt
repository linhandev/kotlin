// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 103 -> sentence 103
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * syntax-and-grammar, syntax-grammar -> paragraph 106 -> sentence 106
 * NUMBER: 3
 * DESCRIPTION: callSuffix typeArguments with valueArguments
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p103.pos3

fun <T> pick(v: T): T = v

fun box(): String = if (pick<String>("x") == "x") "OK" else "NOK"
