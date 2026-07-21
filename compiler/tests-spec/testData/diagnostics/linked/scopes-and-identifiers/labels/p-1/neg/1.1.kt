// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, labels -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: break@missing with undeclared label reports NOT_A_LOOP_LABEL and UNRESOLVED_REFERENCE
 */

// TESTCASE NUMBER: 1
fun case1() {
    loop@ while (true) {
        <!NOT_A_LOOP_LABEL!>break<!UNRESOLVED_REFERENCE!>@missing<!><!>
    }
}
