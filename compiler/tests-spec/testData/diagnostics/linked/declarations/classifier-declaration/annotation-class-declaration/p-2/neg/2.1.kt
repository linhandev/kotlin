// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: annotation constructor parameters cannot use custom class or Array element types
 */

// TESTCASE NUMBER: 1
class MyClass

annotation class Bad1(val p: <!INVALID_TYPE_OF_ANNOTATION_MEMBER!>MyClass<!>)

// TESTCASE NUMBER: 2
annotation class Bad2(val p: <!INVALID_TYPE_OF_ANNOTATION_MEMBER!>Array<Int><!>)
