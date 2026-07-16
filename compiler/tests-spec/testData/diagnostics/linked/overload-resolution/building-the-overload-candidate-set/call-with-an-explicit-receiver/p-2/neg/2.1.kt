// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-an-explicit-receiver -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: explicit type receiver call fails when static member is missing on classifier type
 */

class Type11202TN

// TESTCASE NUMBER: 1
fun case_1(): Int = Type11202TN.<!UNRESOLVED_REFERENCE!>missing11202TN<!>()
