// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, abstract-classes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Base511() reports CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS
 */

abstract class Base511 {
    abstract fun foo(): Int
}

// TESTCASE NUMBER: 1
fun case1() {
    val direct = <!CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS!>Base511()<!>
}
