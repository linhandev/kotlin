// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: object constructed via primary constructor does not execute secondary constructor body
 */

// TESTCASE NUMBER: 1
class C(val x: Int) {
    companion object {
        var secondaryRan = false
    }

    constructor() : this(0) {
        secondaryRan = true
    }
}
