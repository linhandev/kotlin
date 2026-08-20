// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 303 -> sentence 303
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 303 -> sentence 303
 * NUMBER: 1
 * DESCRIPTION: inner class cannot be declared in an interface
 */

// TESTCASE NUMBER: 1
interface I {
    <!WRONG_MODIFIER_CONTAINING_DECLARATION!>inner<!> class Bad
}
