// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: cannot access property before initialization regardless of constructor used
 */

// TESTCASE NUMBER: 1
class C(val x: Int) {
    val y = <!UNINITIALIZED_VARIABLE!>z<!>
    val z = x

    constructor() : this(0)
}
