// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 2 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlinFile invalid importHeader keyword identifier
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p2.neg2

import <!SYNTAX!>return<!>
private object InvalidImportHeaderAnchor { const val CASE = 2 }
