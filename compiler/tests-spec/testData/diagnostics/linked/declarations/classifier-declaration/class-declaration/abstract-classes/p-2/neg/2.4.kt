// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT
// FULL_JDK
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: inner and anonymous subclasses fail to implement all inherited abstract members
 */

class MainClass {
    abstract class Base1() {
        abstract val a: CharSequence
        abstract var b: CharSequence
        abstract fun foo(): CharSequence
    }

    abstract class Base2 : Base1() {
        abstract fun boo(x: Int = 10)
    }

    abstract class Base3(override val a: CharSequence) : Base1()
}

// NOTE: absctract class member is not implemented in inner class
// TESTCASE NUMBER: 1
class Case1 {

    abstract inner class ImplBase2() : MainClass.Base2() {
        override var b: CharSequence = ""
        override val a: CharSequence = ""
        override fun boo(x: Int) {}
    }

    inner

    <!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class ImplBase2_1<!> : ImplBase2() {
        override var b: CharSequence = ""
        override fun boo(x: Int) {}
    }
}

// NOTE:absctract class member is not implemented in anonymos class
// TESTCASE NUMBER: 2
class Case2() {
    abstract inner class Impl(override val a: CharSequence) : MainClass.Base3(a)

    fun boo() {
        val impl = <!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>object<!> : Impl("a") {
            override fun foo(): CharSequence = "foo"
        }
    }
}

// NOTE: check abstract member cannot be accessed directly
// TESTCASE NUMBER: 3
class Case3(override val <!REDECLARATION!>boo<!>: String) : BaseCase3() {
    override val zoo: String = super.<!ABSTRACT_SUPER_CALL!>foo<!>()
    override val <!REDECLARATION!>boo<!>: String = super.<!ABSTRACT_SUPER_CALL!>boo<!>
    override val value: String = super.<!ABSTRACT_SUPER_CALL!>zoo<!>
    val hoo: String = super.<!ABSTRACT_SUPER_CALL!>zoo<!>

    override fun foo(): String {
        super.<!ABSTRACT_SUPER_CALL!>foo<!>()
        super.<!ABSTRACT_SUPER_CALL!>boo<!>
        super.value
        return ""
    }
}

abstract class BaseCase3{
    abstract fun foo(): String
    open val value: String get() = "value"
    abstract val boo: String
    abstract val zoo: String
}

// NOTE: abstract class implements kotlin interface
// TESTCASE NUMBER: 4

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Case4<!>(a: String) : BaseCase4(a)

interface InterfaceCase4 {
    fun foo(): String

    fun boo() {
        foo()
    }
}

abstract class BaseCase4(val a: String) : InterfaceCase4

// NOTE: abstract class implements java interface
// TESTCASE NUMBER: 5

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Case5<!>(a: String) : BaseCase5(a)

abstract class BaseCase5(val a: String) : java.util.Deque<String>

// NOTE: abstract class implements java abstract class
// TESTCASE NUMBER: 6

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Case6<!>(a: String) : BaseCase6(a)

abstract class BaseCase6(val a: String) : java.util.AbstractCollection<String>()
