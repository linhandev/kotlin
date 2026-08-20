// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 294 -> sentence 294
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 294 -> sentence 294
 * NUMBER: 1
 * DESCRIPTION: inner class cannot be constructed without an outer instance receiver
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner
}

fun case_1() = Outer.<!RESOLUTION_TO_CLASSIFIER!>Inner<!>()
