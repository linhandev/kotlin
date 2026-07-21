// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: initialization cycle through init block and property
 */

// TESTCASE NUMBER: 1
class C {
    val value: String
    init {
        value = <!UNINITIALIZED_VARIABLE!>other<!>
    }
    val other = "ok"
}
