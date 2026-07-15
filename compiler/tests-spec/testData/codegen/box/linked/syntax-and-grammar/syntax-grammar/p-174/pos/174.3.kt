// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 174 -> sentence 174
 * NUMBER: 3
 * DESCRIPTION: simpleIdentifier soft keyword by as import alias
 */
package syntax.grammar.p174.pos3

import kotlin.math.abs as by

// TESTCASE NUMBER: 1
fun box(): String = if (by(-1) == 1) "OK" else "NOK"
