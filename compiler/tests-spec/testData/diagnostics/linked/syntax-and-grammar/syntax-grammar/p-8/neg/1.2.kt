// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 8 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: importHeader numeric identifier import 123
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p8.neg2

import <!SYNTAX!>456<!>
private object TopLevelReturnAnchor { const val CASE = 2 }
