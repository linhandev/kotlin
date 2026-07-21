// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: nested run block reads outer val outer = 10
 */

// TESTCASE NUMBER: 1
fun case1() {
    val outer = 10
    run {
        val inner = outer + 1
        inner
    }
}
