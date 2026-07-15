// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 6 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: packageHeader with keyword identifier package return
 */

// TESTCASE NUMBER: 1
package <!SYNTAX!>return<!>
private object InvalidPackageEmptyAnchor { const val CASE = 3 }
