// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: property initializer cannot access uninitialized sibling property
 */

// TESTCASE NUMBER: 1
class C {
    val first = <!UNINITIALIZED_VARIABLE!>second<!> + 1
    val second = 2
}
