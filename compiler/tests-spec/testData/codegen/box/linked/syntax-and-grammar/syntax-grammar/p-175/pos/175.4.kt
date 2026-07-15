// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 175 -> sentence 175
 * NUMBER: 4
 * DESCRIPTION: identifier qualified chain with four simpleIdentifier segments
 */
package syntax.grammar.p175.pos4

import kotlin.reflect.KMutableProperty1

// TESTCASE NUMBER: 1
fun box(): String = if (KMutableProperty1::class.simpleName == "KMutableProperty1") "OK" else "NOK"
