// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: abstract class object literal must implement abstract members
 */

// TESTCASE NUMBER: 1
abstract class A {
    abstract fun f(): Int
}

fun case_1() = <!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>object<!> : A() {}
