// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: structural equality not applicable between different value class types
 */

// TESTCASE NUMBER: 1
@JvmInline
value class A(val x: Int)

@JvmInline
value class B(val y: Int)

fun test(a: A, b: B): Boolean = <!EQUALITY_NOT_APPLICABLE!>a == b<!>
