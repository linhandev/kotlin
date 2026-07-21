// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: delegate expression type must be subtype of delegated interface
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(value: Int): Double
}

class C(delegatee: String) : I by <!TYPE_MISMATCH!>delegatee<!>
