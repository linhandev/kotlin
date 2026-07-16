// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, inheriting -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: PrivateDerived531 call to base private hidden() and SecretUser531 call to interface private hidden() report INVISIBLE_MEMBER
 */

// TESTCASE NUMBER: 1
open class PrivateBase531 {
    private fun hidden(): Int = 1
}

class PrivateDerived531 : PrivateBase531() {
    fun tryAccess() {
        <!INVISIBLE_MEMBER!>hidden<!>()
    }
}

// TESTCASE NUMBER: 2
interface Secret531 {
    private fun hidden(): Int = 1
}

class SecretUser531 : Secret531 {
    fun tryAccess() {
        <!INVISIBLE_MEMBER!>hidden<!>()
    }
}
