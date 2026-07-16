// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: object type cannot be used as supertype
 */

// TESTCASE NUMBER: 1
object Singleton

class Sub : <!SINGLETON_IN_SUPERTYPE!>Singleton<!>
