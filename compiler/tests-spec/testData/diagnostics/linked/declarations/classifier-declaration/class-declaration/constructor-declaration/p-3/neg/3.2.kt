// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: secondary constructor delegating to itself or forming a two-constructor delegation cycle is rejected
 */

// TESTCASE NUMBER: 1
class A1 {
    constructor() : <!CYCLIC_CONSTRUCTOR_DELEGATION_CALL!>this<!>()
}

// TESTCASE NUMBER: 2
class A2 {
    constructor(x1: Int) : <!CYCLIC_CONSTRUCTOR_DELEGATION_CALL!>this<!>(x1, 1)
    constructor(x1: Int, x2: Int) : <!CYCLIC_CONSTRUCTOR_DELEGATION_CALL!>this<!>(x1)
}
