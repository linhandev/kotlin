// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: secondary constructor cannot delegate to super when primary constructor exists
 */

// TESTCASE NUMBER: 1
open class B(x: Int)

class C(x: Int) : B(x) {
    constructor() : <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>super<!>(1)
}
