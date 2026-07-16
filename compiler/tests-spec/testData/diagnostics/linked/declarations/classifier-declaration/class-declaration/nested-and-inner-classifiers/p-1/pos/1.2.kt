// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: inner class is instantiated with explicit outer instance receiver expression
 */

// TESTCASE NUMBER: 1
class Foo {
    inner class Inner
}

fun case1(foo: Foo): Foo.Inner = foo.Inner()
