// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 174 -> sentence 174
 * NUMBER: 5
 * DESCRIPTION: simpleIdentifier soft keyword operator as variable name
 */
package syntax.grammar.p174.pos5

val operator = 42

// TESTCASE NUMBER: 1
fun box(): String = if (operator == 42) "OK" else "NOK"
