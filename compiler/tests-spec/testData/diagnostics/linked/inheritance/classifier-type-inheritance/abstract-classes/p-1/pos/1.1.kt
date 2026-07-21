// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, abstract-classes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: ConcreteImpl overrides abstract foo() and bar from AbstractBase
 */

abstract class AbstractBase {
    abstract fun foo(): Int
    abstract val bar: String
}

class ConcreteImpl : AbstractBase() {
    override fun foo(): Int = 42
    override val bar: String = "ok"
}

// TESTCASE NUMBER: 1
fun case1(): Int {
    val impl: AbstractBase = ConcreteImpl()
    impl.bar
    return impl.foo()
}
