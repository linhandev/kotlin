// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 299 -> sentence 299
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 299 -> sentence 299
 * NUMBER: 1
 * DESCRIPTION: nested class cannot access outer instance members directly
 */

// TESTCASE NUMBER: 1
class Outer(val id: Int) {
    class Nested {
        fun read(): Int = <!UNRESOLVED_REFERENCE!>id<!>
    }
}
