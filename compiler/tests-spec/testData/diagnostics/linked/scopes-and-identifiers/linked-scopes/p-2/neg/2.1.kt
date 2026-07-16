// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: a in sibling run block reports UNRESOLVED_REFERENCE
 */

// TESTCASE NUMBER: 1
fun case1() {
    run {
        val a = 1
    }
    run {
        val b = <!UNRESOLVED_REFERENCE!>a<!>
    }
}
