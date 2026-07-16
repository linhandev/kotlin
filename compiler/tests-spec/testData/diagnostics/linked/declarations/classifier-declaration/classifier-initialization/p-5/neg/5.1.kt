// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: property initializer referencing a later property and self-referencing initializer cause initialization errors
 */

// TESTCASE NUMBER: 1
class C {
    val a: Int = <!UNINITIALIZED_VARIABLE!>b<!>
    val b: Int = 2
}

// TESTCASE NUMBER: 2
class D {
    val x = 1 + <!DEBUG_INFO_MISSING_UNRESOLVED, TYPECHECKER_HAS_RUN_INTO_RECURSIVE_PROBLEM_ERROR!>x<!>
}
