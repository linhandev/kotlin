// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: secondary constructor delegates to primary and has body
 */

// TESTCASE NUMBER: 1
class A(val x: Int) {
    constructor() : this(1) {
        val y = x + 1
    }
}
