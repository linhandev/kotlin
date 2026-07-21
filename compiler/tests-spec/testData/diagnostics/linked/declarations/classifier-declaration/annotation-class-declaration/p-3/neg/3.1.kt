// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: annotation constructor parameter cannot use type parameter
 */

// TESTCASE NUMBER: 1
annotation class Bad<T>(val x: <!INVALID_TYPE_OF_ANNOTATION_MEMBER!>T<!>)
