// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: init block cannot access property declared later in class body
 */

// TESTCASE NUMBER: 1
class C {
    init {
        val x = <!UNINITIALIZED_VARIABLE!>b<!>
    }
    val b = 1
}
