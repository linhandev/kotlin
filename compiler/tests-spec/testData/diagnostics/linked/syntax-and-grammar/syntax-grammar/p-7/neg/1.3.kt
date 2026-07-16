// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 7 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: importList invalid keyword in importHeader tail
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p7.neg3

import kotlin.math.<!SYNTAX!>return<!>
private object InvalidImportDotAnchor { const val CASE = 3 }
