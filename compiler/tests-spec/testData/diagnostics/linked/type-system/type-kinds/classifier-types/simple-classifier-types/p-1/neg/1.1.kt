// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, classifier-types, simple-classifier-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Ill-formed simple classifier types violate well-formedness conditions on supertypes
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Base1

interface Invalid1 : Base1<!NULLABLE_SUPERTYPE!>?<!>


// TESTCASE NUMBER: 2
interface Base2

class Derived2 : Base2<!NULLABLE_SUPERTYPE!>?<!>


// TESTCASE NUMBER: 3
open class Base3

class Derived3 : <!SUPERTYPE_NOT_INITIALIZED!>Base3<!NULLABLE_SUPERTYPE!>?<!><!>


// TESTCASE NUMBER: 4
interface Interface4A
interface Interface4B

class Case4 : Interface4A<!NULLABLE_SUPERTYPE!>?<!>, Interface4B<!NULLABLE_SUPERTYPE!>?<!>


// TESTCASE NUMBER: 5
abstract class Abstract5

class Concrete5 : <!SUPERTYPE_NOT_INITIALIZED!>Abstract5<!NULLABLE_SUPERTYPE!>?<!><!>
