// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: anonymous class fails to correctly implement abstract properties and functions
 */

// FILE: TestCase1.kt
package testPackCase1
private abstract class Base {

    abstract val a: Any
    abstract var b: Any
    internal abstract val c: Any
    internal abstract var d: Any


    abstract fun foo()
    internal abstract fun boo(): Any
}

// TESTCASE NUMBER: 1
fun case1() {
    val impl = object : Base() {
        override var a: Any
            get() = TODO()
            set(value) {}
        override <!VAR_OVERRIDDEN_BY_VAL!>val<!> b: Any
            get() = TODO()
        override var c: Any
            get() = TODO()
            set(value) {}
        override <!VAR_OVERRIDDEN_BY_VAL!>val<!> d: Any
            get() = TODO()

        override fun foo() {}

        override fun boo(): Any {
            return ""
        }
    }
}

// FILE: TestCase2.kt
// NOTE: property is not implemented
package testPackCase2
private abstract class Base {

    abstract val a: Any
    abstract var b: Any
    internal abstract val c: Any
    internal abstract var d: Any


    abstract fun foo()
    internal abstract fun boo(): Any
}
// TESTCASE NUMBER: 2

fun case2() {
    val impl = <!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>object<!> : Base() {
        override var b: Any
            get() = TODO()
            set(value) {}
        override val c: Any
            get() = TODO()
        override var d: Any
            get() = TODO()
            set(value) {}

        override fun foo() {
            TODO()
        }

        override fun boo(): Any {
            TODO()
        }
    }
}


// FILE: TestCase3.kt
package testPackCase3
private abstract class Base {

    abstract val a: Any
    abstract var b: Any
    internal abstract val c: Any
    internal abstract var d: Any


    abstract fun foo()
    internal abstract fun boo(): Any
}

// TESTCASE NUMBER: 3
fun case3() {
    val impl = <!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>object<!> : Base() {
        override var b: Any
            get() = TODO()
            set(value) {}
        override val c: Any
            get() = TODO()
        override var d: Any
            get() = TODO()
            set(value) {}

        override fun foo() {}

        override fun boo(): Any {
            return 1
        }
    }
}
