// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: direct instantiation of abstract class is forbidden
 */

abstract class Base0()

abstract class Base1() {
    abstract fun foo()
}

abstract class Base2(var b1: Any, val a1: Any) {
    abstract fun foo()
}

// TESTCASE NUMBER: 1
fun case1() {
    val b0 = <!CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS!>Base0()<!>
    val b1 = <!CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS!>Base1()<!>
    val b2 = <!CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS!>Base2(1, "1")<!>
}
