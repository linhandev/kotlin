// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: class declaration multiple class inheritance is not supported
 */

// TESTCASE NUMBER: 1
open class Base1
open class Base2

class Case1 : Base1(), <!MANY_CLASSES_IN_SUPERTYPE_LIST!>Base2<!>()
