// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 192 -> sentence 192
 * PRIMARY LINKS: inheritance, overriding -> paragraph 192 -> sentence 192
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 192 -> sentence 192
 *                declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 192 -> sentence 192
 *                inheritance, inheriting -> paragraph 192 -> sentence 192
 * NUMBER: 1
 * DESCRIPTION: type inference when an abstract class re-declares a concrete open member as abstract override in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
    fun read(): Int = f()
}

abstract class Mid : Base() {
    abstract override fun f(): Int
}

class Leaf : Mid() {
    override fun f(): Int = 2
}

fun case1() {
    val leaf = Leaf()
    leaf checkType { check<Leaf>() }
    checkSubtype<Mid>(leaf)
    checkSubtype<Base>(leaf)
    leaf.f() checkType { check<Int>() }
    leaf.read() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Meter {
    open val reading: Int = 1
}

abstract class AbstractMeter : Meter() {
    abstract override val reading: Int
}

class ConcreteMeter : AbstractMeter() {
    override val reading: Int = 7
}

fun case2() {
    val m = ConcreteMeter()
    m checkType { check<ConcreteMeter>() }
    checkSubtype<AbstractMeter>(m)
    m.reading checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
open class Named {
    open fun name(): String = "base"
}

abstract class NeedsName : Named() {
    abstract override fun name(): String
}

class LeafNamed : NeedsName() {
    override fun name(): String = "leaf"
}

fun case3() {
    val n = LeafNamed()
    n checkType { check<LeafNamed>() }
    checkSubtype<NeedsName>(n)
    n.name() checkType { check<String>() }
}
