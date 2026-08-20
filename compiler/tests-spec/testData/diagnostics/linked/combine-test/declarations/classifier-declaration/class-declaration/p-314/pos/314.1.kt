// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 314 -> sentence 314
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 314 -> sentence 314
 * NUMBER: 1
 * DESCRIPTION: precise types for object declaration nested in a class acts as a singleton nested classifier
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    object Token {
        val v = 1
    }
}

fun case_1() {
    Outer.Token.v checkType { check<Int>() }
    Outer.Token checkType { check<Outer.Token>() }
}
