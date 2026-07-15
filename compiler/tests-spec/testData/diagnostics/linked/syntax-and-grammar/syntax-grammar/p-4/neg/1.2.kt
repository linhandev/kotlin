// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 4 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: shebangLine optional file invalid EOF token
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p4.neg3

import <!SYNTAX!>2<!>
private object ImportListTrailingAnchor { const val CASE = 3 }
