// FIR_IDENTICAL
// DIAGNOSTICS: -UNINITIALIZED_VARIABLE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: secondary constructor cannot access instance members before super call
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class A : Base {
    val p = 1

    constructor() : super(<!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>p<!>)
}
