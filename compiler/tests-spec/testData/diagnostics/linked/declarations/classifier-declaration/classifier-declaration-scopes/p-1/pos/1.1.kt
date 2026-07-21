// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: companion object factory accesses private constructor; inner class reads outer private property
 */

// TESTCASE NUMBER: 1
class C private constructor(val secret: Int) {
    companion object {
        fun create(): C = C(42)
        fun readSecret(c: C) = c.secret
    }
}

// TESTCASE NUMBER: 2
class Outer {
    private val x = 1
    inner class Inner {
        fun read() = x
    }
}
