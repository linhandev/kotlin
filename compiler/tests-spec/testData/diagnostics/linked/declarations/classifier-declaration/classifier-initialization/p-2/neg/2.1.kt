// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: secondary constructor must delegate to primary or super
 */

// TESTCASE NUMBER: 1
class A(val x: Int) {
    <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>constructor()<!>
}

// TESTCASE NUMBER: 2
open class B(x: Int)
class C(x: Int) : B(x) {
    constructor() : <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>super<!>(1)
}
