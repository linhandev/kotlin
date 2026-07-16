// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: without smart cast source nullable receiver requires safe call
 */

// TESTCASE NUMBER: 1
fun case_1(s: String?) {
    s<!UNSAFE_CALL!>.<!>length
}
