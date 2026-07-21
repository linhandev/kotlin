// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: Token512() constructor call on sealed class reports SEALED_CLASS_CONSTRUCTOR_CALL
 */

sealed class Token512

// TESTCASE NUMBER: 1
fun case1() {
    val token = <!SEALED_CLASS_CONSTRUCTOR_CALL!><!INVISIBLE_MEMBER!>Token512<!>()<!>
}
