// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: concrete and nested abstract subclasses correctly declare abstract members
 */


abstract class Base() {
    abstract fun foo()

    abstract val a: String
}

class Impl : Base() {
    override fun foo(): Unit {
        TODO("not implemented")
    }

    override val a: String
        get() = TODO("not implemented")

}

// TESTCASE NUMBER: 1
fun case1() {
    val impl = Impl()
}

abstract class Case2() : BaseCase2() {
    abstract fun boo()
    public abstract override fun foo()
}

abstract class BaseCase2() {
    abstract val a: String
    protected abstract fun foo()
}


interface MyInterfaceCase3 {
    abstract fun foo(): String
    abstract val a: String
}

abstract class MyImplCase3() : MyInterfaceCase3 {
    abstract fun boo()
}
