// FIR_IDENTICAL
// LANGUAGE: +InlineClasses
// DIAGNOSTICS: -INLINE_CLASS_DEPRECATED
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: forbidden identity equality on different value class types
 */

// TESTCASE NUMBER: 1
inline class A(val x: Int)
inline class B(val x: Int)

fun test(a: A, b: B): Boolean = <!EQUALITY_NOT_APPLICABLE, FORBIDDEN_IDENTITY_EQUALS!>a === b<!>
