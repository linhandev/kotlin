// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: extension receiver type must be a valid type
 */

// TESTCASE NUMBER: 1
fun <!UNRESOLVED_REFERENCE!>UnknownType<!>.missingReceiver() {}
