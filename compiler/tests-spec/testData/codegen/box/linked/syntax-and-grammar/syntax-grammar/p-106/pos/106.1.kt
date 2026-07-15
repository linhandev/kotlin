// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 106 -> sentence 106
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 107 -> sentence 107
 * NUMBER: 1
 * DESCRIPTION: valueArguments multiple arguments
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p106.pos1

fun sum(a: Int, b: Int): Int = a + b

fun box(): String { val ok = sum(1, 2) == 3; check(ok); return "OK" }
