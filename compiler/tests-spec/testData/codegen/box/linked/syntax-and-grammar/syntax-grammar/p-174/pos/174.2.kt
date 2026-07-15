// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 174 -> sentence 174
 * NUMBER: 2
 * DESCRIPTION: simpleIdentifier soft keyword data as class name
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p174.pos2

class data(val value: Int)

fun box(): String = if (data(1).value == 1) "OK" else "NOK"
