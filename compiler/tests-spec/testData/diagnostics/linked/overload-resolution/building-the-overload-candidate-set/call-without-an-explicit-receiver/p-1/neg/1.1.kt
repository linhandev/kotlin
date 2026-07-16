// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-without-an-explicit-receiver -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: simple-path call with no matching top-level or implicit receiver callable fails
 */

// TESTCASE NUMBER: 1
fun case_1(): Int = <!UNRESOLVED_REFERENCE!>missing11205<!>()
