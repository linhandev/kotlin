// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: case1 call to protected secret() on Derived641Neg from outside class reports INVISIBLE_MEMBER
 */

// TESTCASE NUMBER: 1
open class Base641Neg {
    protected fun secret(): String = "hidden"
}

class Derived641Neg : Base641Neg()

fun case1(d: Derived641Neg): String = d.<!INVISIBLE_MEMBER!>secret<!>()
