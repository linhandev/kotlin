// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 3 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: script invalid topLevel extra token
 */

// TESTCASE NUMBER: 1

import <!SYNTAX!>1<!>
private object InvalidImportAliasAnchor { const val CASE = 2 }
