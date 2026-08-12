// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 386 -> sentence 386
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 386 -> sentence 386
 *                declarations, function-declaration -> paragraph 386 -> sentence 386
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 386 -> sentence 386
 * NUMBER: 1
 * DESCRIPTION: non-inner nested class cannot call outer instance private fun
 */

// TESTCASE NUMBER: 1
class Outer {
    private fun secret(): Int = 7
    class Nested {
        fun get(): Int = <!UNRESOLVED_REFERENCE!>secret<!>()
    }
}
