// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: if and else branches both read shared from enclosing function
 */

// TESTCASE NUMBER: 1
fun case1(flag: Boolean) {
    val shared = 0
    if (flag) {
        shared + 1
    } else {
        shared + 2
    }
}
