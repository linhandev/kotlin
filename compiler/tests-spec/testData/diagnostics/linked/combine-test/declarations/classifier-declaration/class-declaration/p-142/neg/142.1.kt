// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 142 -> sentence 142
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 142 -> sentence 142
 *                declarations, classifier-declaration, interface-declaration -> paragraph 142 -> sentence 142
 * NUMBER: 1
 * DESCRIPTION: interface cannot declare anonymous initializer blocks; init belongs to implementing class declarations
 */

// TESTCASE NUMBER: 1
interface Bare {
    <!ANONYMOUS_INITIALIZER_IN_INTERFACE!>init<!> {
    }
}

// TESTCASE NUMBER: 2
interface WithMembers {
    val id: Int
    fun tag(): String
    <!ANONYMOUS_INITIALIZER_IN_INTERFACE!>init<!> {
    }
}

// TESTCASE NUMBER: 3
fun interface Runnable {
    fun run()
    <!ANONYMOUS_INITIALIZER_IN_INTERFACE!>init<!> {
    }
}
