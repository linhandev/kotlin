// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: delegate expression cannot access classifier members during initialization
 */

// TESTCASE NUMBER: 1
interface I

open class S(n: Int) : I

class A : I by S(
    <!UNRESOLVED_REFERENCE!>a<!>
) {
    val a = 1
}
