// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: innermost run block reads outer and middle locals
 */

// TESTCASE NUMBER: 1
fun case1() {
    val outer = 1
    run {
        val middle = outer
        run {
            val inner = middle + outer
            inner
        }
    }
}
