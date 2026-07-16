// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: capturing uninitialized property during subsequent initialization
 */

// TESTCASE NUMBER: 1
class E {
    val message: String = "tail is ${<!UNINITIALIZED_VARIABLE!>tail<!>}"
    val tail = 7
}
