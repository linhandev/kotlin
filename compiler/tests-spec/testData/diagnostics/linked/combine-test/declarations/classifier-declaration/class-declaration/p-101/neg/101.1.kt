// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 101 -> sentence 101
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 101 -> sentence 101
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 101 -> sentence 101
 * NUMBER: 1
 * DESCRIPTION: secondary constructor cannot delegate only to super() when class has primary constructor in class declaration
 */

// TESTCASE NUMBER: 1
class User(val name: String) : <!SUPERTYPE_NOT_INITIALIZED!>Any<!> {
    constructor() : <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>super<!>()
}
