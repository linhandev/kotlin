// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: interface cannot declare property initializer, delegated property, class superclass, or inner nested class
 */

// TESTCASE NUMBER: 1
interface I {
    val prop = <!PROPERTY_INITIALIZER_IN_INTERFACE!>1<!>
}

// TESTCASE NUMBER: 2
interface I2 {
    val prop2 <!DELEGATED_PROPERTY_IN_INTERFACE!>by <!DELEGATE_SPECIAL_FUNCTION_AMBIGUITY!>TODO()<!><!>
}

// TESTCASE NUMBER: 3
open class Base

interface I3 : <!INTERFACE_WITH_SUPERCLASS!>Base<!>

// TESTCASE NUMBER: 4
interface I4 {
    <!WRONG_MODIFIER_CONTAINING_DECLARATION!>inner<!> class Nested
}
