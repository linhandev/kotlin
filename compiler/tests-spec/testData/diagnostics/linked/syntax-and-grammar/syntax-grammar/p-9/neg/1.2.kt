// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 9 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: importAlias numeric alias import x as 123
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p9.neg2

import kotlin.math.abs as <!SYNTAX!>123<!>
private object InvalidImportAliasWhenAnchor { const val CASE = 2 }
