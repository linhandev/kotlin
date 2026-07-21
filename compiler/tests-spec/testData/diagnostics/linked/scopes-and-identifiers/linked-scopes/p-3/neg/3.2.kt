// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: top-level reference to fun local reports UNRESOLVED_REFERENCE
 */

// TESTCASE NUMBER: 1
fun case1() {
    val local = 1
}

val global = <!UNRESOLVED_REFERENCE!>local<!>
