// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 7 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: importList multiple importHeader entries
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p7.pos2

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

val case1: Int = abs(-1) + max(1, 3) + min(2, 5)
