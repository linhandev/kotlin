// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: inner class constructor requires outer class receiver
 */

// TESTCASE NUMBER: 1
class Foo {
    inner class Inner
}

fun case1(): Foo.Inner = Foo.<!RESOLUTION_TO_CLASSIFIER!>Inner<!>()
