// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 304 -> sentence 304
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 304 -> sentence 304
 * NUMBER: 1
 * DESCRIPTION: nested class name must be qualified with the outer class name
 */

// TESTCASE NUMBER: 1
class Outer {
    class Nested
}

fun case_1(): String = <!UNRESOLVED_REFERENCE!>Nested<!>::class.<!DEBUG_INFO_MISSING_UNRESOLVED!>simpleName<!>!!
