// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 122 -> sentence 122
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 122 -> sentence 122
 *                declarations, property-declaration -> paragraph 122 -> sentence 122
 * NUMBER: 1
 * DESCRIPTION: val property without initializer remains uninitialized when init block does not assign it in class declaration
 */

// TESTCASE NUMBER: 1
class Unset {
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>val x: Int<!>

    init {
    }
}

// TESTCASE NUMBER: 2
class UnsetWithParam(val marker: Boolean) {
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>val code: Int<!>

    init {
        check(marker || !marker)
    }
}
