// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// FULL_JDK

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: inheritance, overriding -> paragraph 7 -> sentence 7
 * NUMBER: 10
 * DESCRIPTION: concrete class, inner class, anonymous object, and sealed subclass fail to implement inherited abstract members
 * ISSUES: KT-27825
 */

// TESTCASE NUMBER: 1
<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Case1<!>() : BaseCase1(), InterfaceCase1

abstract class BaseCase1() {
    abstract fun foo(): String
    abstract val a: String
}

interface InterfaceCase1 {
    fun foo() = "foo"
    val a: String
        get() = "a"
}

// TESTCASE NUMBER: 2
class Case2Outer {
    val v = "v"

    abstract class Case2Base() {
        abstract fun foo(): String
    }

    inner
    <!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class A<!>() : Case2Base()
}

// TESTCASE NUMBER: 3
fun case3() {
    <!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>object<!> : CaseOuter.CaseBase() {}.outerFoo()
}

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class B<!>() : CaseOuter.CaseBase()

sealed class CaseOuter {
    val v = "v"
    abstract fun outerFoo();

    abstract class CaseBase() : CaseOuter() {
        abstract fun foo(): String
    }

    <!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class A<!>() : CaseBase()
}