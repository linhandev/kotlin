// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 3 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: script fileAnnotation packageHeader importList statements
 */

// TESTCASE NUMBER: 1
@file:Suppress("UNUSED_EXPRESSION")

package syntax.grammar.p3.pos2

import kotlin.math.max

fun case1(): Int = max(1, 5)
