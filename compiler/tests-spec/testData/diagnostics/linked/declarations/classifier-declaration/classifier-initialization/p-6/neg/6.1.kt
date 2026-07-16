// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: accessing property before definite initialization in init block
 */

// TESTCASE NUMBER: 1
class C {
    init {
        println(<!UNINITIALIZED_VARIABLE!>value<!>)
    }
    val value = 1
}

// TESTCASE NUMBER: 2
class D(val cond: Boolean) {
    val x: Int
    init {
        if (cond) {
            println(<!UNINITIALIZED_VARIABLE!>x<!>)
        }
        x = 1
    }
}
