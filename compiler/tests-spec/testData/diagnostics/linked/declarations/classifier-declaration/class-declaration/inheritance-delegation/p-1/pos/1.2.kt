// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: class overrides delegated interface method in body
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(value: Int): Double
}

class Impl : I {
    override fun foo(value: Int): Double = value.toDouble()
}

class C(delegatee: I) : I by delegatee {
    override fun foo(value: Int): Double = 0.0
}
