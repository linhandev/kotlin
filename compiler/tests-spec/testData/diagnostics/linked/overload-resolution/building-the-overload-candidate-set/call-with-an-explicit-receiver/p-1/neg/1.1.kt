// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-an-explicit-receiver -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: explicit receiver call fails when member is not accessible on receiver type
 */

class Host11202N

// TESTCASE NUMBER: 1
fun case_1(): Int = Host11202N().<!UNRESOLVED_REFERENCE!>read11202<!>()
