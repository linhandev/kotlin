// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 9 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: importAlias missing as keyword
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p9.neg3

import kotlin.math.abs <!SYNTAX!>WrongAlias<!>
private object InvalidImportAliasForAnchor { const val CASE = 3 }
