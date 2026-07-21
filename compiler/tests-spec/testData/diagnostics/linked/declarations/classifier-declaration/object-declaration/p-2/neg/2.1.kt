// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: object cannot contain companion object, type parameters, explicit constructor, or inner class
 */

// TESTCASE NUMBER: 1
object O {
    <!WRONG_MODIFIER_CONTAINING_DECLARATION!>companion<!> object Nested
}

// TESTCASE NUMBER: 2
object Typed<!TYPE_PARAMETERS_IN_OBJECT!><T><!>

// TESTCASE NUMBER: 3
object WithCtor {
    <!CONSTRUCTOR_IN_OBJECT!>constructor()<!> {}
}

// TESTCASE NUMBER: 4
object WithInner {
    <!WRONG_MODIFIER_CONTAINING_DECLARATION!>inner<!> class Nested
}
