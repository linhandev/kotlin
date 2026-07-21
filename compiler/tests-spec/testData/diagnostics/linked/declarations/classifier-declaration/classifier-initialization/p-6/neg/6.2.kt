// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: reading property before definite initialization in init block
 */

// TESTCASE NUMBER: 1
class Service {
    val token: String
    init {
        println(<!UNINITIALIZED_VARIABLE!>token<!>)
        token = "ready"
    }
}
