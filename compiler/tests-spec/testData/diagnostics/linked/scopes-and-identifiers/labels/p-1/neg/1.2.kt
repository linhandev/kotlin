// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, labels -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: break@target643 on enclosing function name reports NOT_A_LOOP_LABEL
 */

// TESTCASE NUMBER: 1
fun target643() {
    loop@ while (true) {
        <!NOT_A_LOOP_LABEL!>break@target643<!>
    }
}
