// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, inheriting -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: OpenDerived531 calls inherited public pub() and protected prot()
 */

// TESTCASE NUMBER: 1
open class OpenBase531 {
    public open fun pub() {}
    protected open fun prot() {}
}

class OpenDerived531 : OpenBase531() {
    fun useInherited() {
        pub()
        prot()
    }
}

fun case1(d: OpenDerived531) {
    d.useInherited()
}
