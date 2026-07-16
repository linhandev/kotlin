// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 8 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: importHeader invalid keyword in qualified name tail
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p8.neg3

import kotlin.collections.<!SYNTAX!>return<!>
private object TopLevelIfAnchor { const val CASE = 3 }
