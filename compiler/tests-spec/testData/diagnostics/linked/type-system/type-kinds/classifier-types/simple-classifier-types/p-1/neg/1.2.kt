// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, classifier-types, simple-classifier-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Classifier types with invalid supertype references violate well-formedness
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Base6

sealed class Sealed6 : Base6<!NULLABLE_SUPERTYPE!>?<!>


// TESTCASE NUMBER: 2
interface Iface7

enum class Enum7 : Iface7<!NULLABLE_SUPERTYPE!>?<!> { A, B }


// TESTCASE NUMBER: 3
interface Base8

object Object8 : Base8<!NULLABLE_SUPERTYPE!>?<!>


// TESTCASE NUMBER: 4
open class Base9A
interface Base9B

open class Middle9 : Base9A(), Base9B<!NULLABLE_SUPERTYPE!>?<!>


// TESTCASE NUMBER: 5
class Case5 : <!UNRESOLVED_REFERENCE!>Unknown<!>
