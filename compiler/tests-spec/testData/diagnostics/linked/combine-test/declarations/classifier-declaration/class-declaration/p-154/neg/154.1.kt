// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 154 -> sentence 154
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 154 -> sentence 154
 *                declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 154 -> sentence 154
 * NUMBER: 1
 * DESCRIPTION: abstract class used as a class supertype still cannot be instantiated directly; only concrete subclasses constructed via constructor delegation are allowed
 */

// TESTCASE NUMBER: 1
abstract class Base(val id: Int)

class Impl : Base(1)

fun case1() {
    val ok = Impl()
    val bad = <!CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS!>Base(1)<!>
}

// TESTCASE NUMBER: 2
abstract class Named(val label: String) {
    abstract fun tag(): String
}

class NamedImpl(label: String) : Named(label) {
    override fun tag(): String = label
}

fun case2() {
    NamedImpl("ok")
    <!CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS!>Named("ok")<!>
}

// TESTCASE NUMBER: 3
interface Tag

abstract class Store(val seed: Int)

class TaggedStore(seed: Int) : Store(seed), Tag

fun case3(): Any {
    val concrete: Store = TaggedStore(3)
    return <!CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS!>Store(3)<!>
}
