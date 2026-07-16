// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 6 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: packageHeader with numeric identifier package 1
 */

// TESTCASE NUMBER: 1
package <!SYNTAX!>1<!>
private object InvalidPackageNumericAnchor { const val CASE = 1 }
