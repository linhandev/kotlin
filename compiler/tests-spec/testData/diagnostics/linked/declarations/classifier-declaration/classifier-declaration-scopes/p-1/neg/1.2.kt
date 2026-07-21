// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: static nested class cannot access outer instance members without receiver
 */

// TESTCASE NUMBER: 1
class Outer {
    val x = 1

    class Nested {
        fun fail() = <!UNRESOLVED_REFERENCE!>x<!>
    }
}
