// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 8 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: importHeader nested package path
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p8.pos5

import kotlin.ranges.until

val case1: Int = (0 until 3).count()
