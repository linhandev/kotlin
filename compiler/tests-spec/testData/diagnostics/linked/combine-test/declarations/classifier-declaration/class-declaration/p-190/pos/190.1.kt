// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 190 -> sentence 190
 * PRIMARY LINKS: inheritance, overriding -> paragraph 190 -> sentence 190
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 190 -> sentence 190
 *                inheritance, inheriting -> paragraph 190 -> sentence 190
 * NUMBER: 1
 * DESCRIPTION: type inference when open override allows a grandchild to further override in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
    fun read(): Int = f()
}

open class Mid : Base() {
    open override fun f(): Int = 2
}

class Leaf : Mid() {
    override fun f(): Int = 3
}

fun case1() {
    val leaf = Leaf()
    leaf checkType { check<Leaf>() }
    checkSubtype<Mid>(leaf)
    checkSubtype<Base>(leaf)
    leaf.f() checkType { check<Int>() }
    leaf.read() checkType { check<Int>() }

    val asMid: Mid = leaf
    asMid.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Meter {
    open val reading: Int = 1
}

open class MidMeter : Meter() {
    open override val reading: Int = 2
}

class LeafMeter : MidMeter() {
    override val reading: Int = 3
}

fun case2() {
    val m = LeafMeter()
    m checkType { check<LeafMeter>() }
    checkSubtype<MidMeter>(m)
    m.reading checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
open class Named {
    open fun name(): String = "base"
}

open class MidNamed : Named() {
    open override fun name(): String = "mid"
}

class LeafNamed : MidNamed() {
    override fun name(): String = "leaf"
}

fun case3() {
    val n = LeafNamed()
    n checkType { check<LeafNamed>() }
    checkSubtype<Named>(n)
    n.name() checkType { check<String>() }
}
