// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, matching-and-subsumption-of-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: fun <T> foo() conflicts with base foo reports CONFLICTING_OVERLOADS
 */

// TESTCASE NUMBER: 1
open class OverloadBase520 {
    open fun foo(): Int = 1
}

class OverloadDerived520 : OverloadBase520() {
    <!CONFLICTING_OVERLOADS!>fun <T> foo()<!> = 2
}
