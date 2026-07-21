// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: private member function and private property are not accessible from outside the declaring class
 */

// TESTCASE NUMBER: 1
class C {
    private fun foo() = 1
}

fun test() {
    C().<!INVISIBLE_REFERENCE!>foo<!>()
}

// TESTCASE NUMBER: 2
class D {
    private val x = 1
}

fun read(d: D) = d.<!INVISIBLE_REFERENCE!>x<!>
