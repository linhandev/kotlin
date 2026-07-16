// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: secondary constructor cannot access this before delegation
 */

// TESTCASE NUMBER: 1
class A(val x: Int) {
    val p = 1

    constructor(y: String) : this(<!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this@A<!>.p + y.length)
}
