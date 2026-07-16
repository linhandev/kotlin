// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 7 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: importList star import
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p7.pos4

import kotlin.math.*

val case1: Int = abs(-1) + max(1, 3)
