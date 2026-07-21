// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, package-header -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: package header is allowed only at file top level
 */

// TESTCASE NUMBER: 1
package pkg1000.first

fun case_1() {
    <!SYNTAX!>package<!> pkg1000.<!UNRESOLVED_REFERENCE!>second<!>
}
