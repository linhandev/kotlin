// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlinFile runtime: packageHeader importList and topLevelObject function execute correctly
 */
package syntax.grammar.p1.pos1

import kotlin.math.abs

// TESTCASE NUMBER: 1
fun box(): String { if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; return if (abs(-1) == 1) "OK" else "NOK" }
