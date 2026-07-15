// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 7 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: importList with importAlias
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p7.pos5

import kotlin.math.abs as absolute

val case1: Int = absolute(-2)
