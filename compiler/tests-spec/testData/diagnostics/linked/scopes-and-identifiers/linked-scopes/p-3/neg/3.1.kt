// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: left declared in if branch referenced in else branch reports UNRESOLVED_REFERENCE
 */

// TESTCASE NUMBER: 1
fun case1(flag: Boolean) {
    if (flag) {
        val left = 1
    } else {
        val right = <!UNRESOLVED_REFERENCE!>left<!>
    }
}
