// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 320 -> sentence 320
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 320 -> sentence 320
 * NUMBER: 1
 * DESCRIPTION: inner class in companion object cannot reference outer instance fields
 */

// TESTCASE NUMBER: 1
class Outer(val x: Int) {
    companion object {
        <!WRONG_MODIFIER_CONTAINING_DECLARATION!>inner<!> class Bad {
            fun f() = <!UNRESOLVED_REFERENCE!>x<!>
        }
    }
}
