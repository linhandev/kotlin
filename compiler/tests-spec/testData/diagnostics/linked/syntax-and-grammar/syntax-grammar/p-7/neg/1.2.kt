// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 7 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: importList malformed import missing identifier
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p7.neg2

import kotlin.<!SYNTAX!>return<!>
private object InvalidImportQualifiedAnchor { const val CASE = 2 }
