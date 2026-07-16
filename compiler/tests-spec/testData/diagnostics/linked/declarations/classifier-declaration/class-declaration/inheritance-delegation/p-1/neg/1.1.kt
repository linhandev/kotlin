// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: inheritance delegation only allowed for interface supertypes
 */

// TESTCASE NUMBER: 1
open class Foo

class Bar : <!DELEGATION_NOT_TO_INTERFACE!>Foo<!> by Foo()
