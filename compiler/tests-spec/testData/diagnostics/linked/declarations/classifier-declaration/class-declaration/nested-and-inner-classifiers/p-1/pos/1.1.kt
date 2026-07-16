// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: nested class is referenced with Outer.Nested qualifier and instantiated without outer instance
 */

// TESTCASE NUMBER: 1
class Foo {
    class Nested
}

fun case1(): Foo.Nested = Foo.Nested()
