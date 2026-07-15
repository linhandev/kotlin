// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 103 -> sentence 103
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 104 -> sentence 104
 * syntax-and-grammar, syntax-grammar -> paragraph 106 -> sentence 106
 * NUMBER: 4
 * DESCRIPTION: callSuffix valueArguments with annotatedLambda trailing lambda
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p103.pos4

fun applyInt(x: Int, block: (Int) -> Int): Int = block(x)

fun box(): String = if (applyInt(1) { it + 1 } == 2) "OK" else "NOK"
