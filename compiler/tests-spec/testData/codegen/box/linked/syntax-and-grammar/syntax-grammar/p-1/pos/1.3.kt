// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: kotlinFile runtime: NL blank lines between package import and topLevelObject
 */
package syntax.grammar.p1.pos3

import kotlin.math.min

// TESTCASE NUMBER: 1
fun box(): String = if (min(3, 5) == 3) "OK" else "NOK"
