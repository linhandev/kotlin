// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: property without invoke operator cannot be called as X()
 */

// TESTCASE NUMBER: 1
val plain1123 = 1

fun case_1(): Int = <!UNRESOLVED_REFERENCE!>plain1123<!>()
