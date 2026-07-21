// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: import alias without simpleIdentifier violates importAlias grammar and reports SYNTAX
 */

// TESTCASE NUMBER: 1
package pkg1004.aliasNeg1

import pkg1004.aliasNeg1.foo as<!SYNTAX!><!>

fun foo() {}

fun case_1() {}
