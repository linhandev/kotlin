// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 4 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: shebangLine optional packageHeader importList topLevelObject
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p4.pos4

import kotlin.math.abs

val case1: Int = abs(-2)
