// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, identifiers-and-paths -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: this in top-level function reports NO_THIS
 */

// TESTCASE NUMBER: 1
fun case1() {
    <!NO_THIS!>this<!>
}
