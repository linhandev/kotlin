// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 174 -> sentence 174
 * NUMBER: 1
 * DESCRIPTION: simpleIdentifier regular Identifier in variable declaration
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p174.pos1

val myIdentifier = 42

fun box(): String = when (myIdentifier) { 42 -> "OK"; else -> "NOK" }
