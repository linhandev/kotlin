// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: object cannot inherit from multiple classes
 */

// TESTCASE NUMBER: 1
open class Base
open class Other

object Dual : Base(), <!MANY_CLASSES_IN_SUPERTYPE_LIST!>Other<!>()
