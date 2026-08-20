// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 129 -> sentence 129
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 129 -> sentence 129
 *                declarations, property-declaration -> paragraph 129 -> sentence 129
 * NUMBER: 1
 * DESCRIPTION: property initializer cannot forward-reference a later property in class declaration
 */

// TESTCASE NUMBER: 1
class IntForward {
    val doubled = <!UNINITIALIZED_VARIABLE!>base<!> * 2
    val base = 5
}

// TESTCASE NUMBER: 2
class SeedForward(val seed: Int) {
    val total = seed + <!UNINITIALIZED_VARIABLE!>extra<!>
    val extra = 10
}

// TESTCASE NUMBER: 3
class ChainForward {
    val third = <!UNINITIALIZED_VARIABLE!>second<!> + 1
    val second = <!UNINITIALIZED_VARIABLE!>first<!> + 1
    val first = 1
}
