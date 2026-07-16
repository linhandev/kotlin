// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 9 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: importAlias used in function body
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p9.pos3

import kotlin.math.abs as absolute

fun case1(): Int = absolute(-3)
