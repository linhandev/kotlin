// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 112 -> sentence 112
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 113 -> sentence 113
 * NUMBER: 1
 * DESCRIPTION: stringLiteral line string
 */
package syntax.grammar.p112.pos1

// TESTCASE NUMBER: 1
fun box(): String { var passed = false; if ("hello".length == 5) passed = true; return if (passed) "OK" else "NOK" }
