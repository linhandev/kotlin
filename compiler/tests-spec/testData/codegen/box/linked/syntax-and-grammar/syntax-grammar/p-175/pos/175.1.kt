// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 175 -> sentence 175
 * NUMBER: 1
 * DESCRIPTION: identifier two-part qualified simpleIdentifier dot simpleIdentifier in import
 */
package syntax.grammar.p175.pos1

import kotlin.collections.emptyList

// TESTCASE NUMBER: 1
fun box(): String = if (emptyList<String>().size == 0) "OK" else "NOK"
