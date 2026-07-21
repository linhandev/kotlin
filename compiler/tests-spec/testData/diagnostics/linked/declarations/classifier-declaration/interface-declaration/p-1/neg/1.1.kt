// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: interface cannot be instantiated by constructor call and cannot declare an explicit constructor
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): Int
}

fun testInstantiate(): I = <!RESOLUTION_TO_CLASSIFIER!>I<!>()

// TESTCASE NUMBER: 2
interface I2 <!CONSTRUCTOR_IN_INTERFACE!>constructor()<!>
