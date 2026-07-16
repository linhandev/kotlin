// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: function parameter must have a name; parameter with missing identifier is rejected
 */

// TESTCASE NUMBER: 1
fun broken(<!SYNTAX!><!>: Int) {}
