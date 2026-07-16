// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 9 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: importAlias with descriptive alias name
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p9.pos2

import kotlin.collections.listOf as listOfAlias

val case1: Int = listOfAlias(1, 2).size
