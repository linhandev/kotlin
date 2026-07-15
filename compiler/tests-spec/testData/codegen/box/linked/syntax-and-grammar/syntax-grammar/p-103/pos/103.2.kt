// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 103 -> sentence 103
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 104 -> sentence 104
 * syntax-and-grammar, syntax-grammar -> paragraph 119 -> sentence 119
 * NUMBER: 2
 * DESCRIPTION: callSuffix annotatedLambda trailing lambda
 */
package syntax.grammar.p103.pos2

// TESTCASE NUMBER: 1
fun box(): String = if (run { 2 } == 2) "OK" else "NOK"
