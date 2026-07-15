// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 127 -> sentence 127
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * syntax-and-grammar, syntax-grammar -> paragraph 69 -> sentence 69
 * NUMBER: 1
 * DESCRIPTION: ifExpression conditional expression
 */
package syntax.grammar.p127.pos1

// TESTCASE NUMBER: 1
fun box(): String = if ((if (1 < 2) "a" else "b") == "a") "OK" else "NOK"
