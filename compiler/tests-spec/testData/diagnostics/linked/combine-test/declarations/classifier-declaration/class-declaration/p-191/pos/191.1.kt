// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 191 -> sentence 191
 * PRIMARY LINKS: inheritance, overriding -> paragraph 191 -> sentence 191
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 191 -> sentence 191
 *                inheritance, inheriting -> paragraph 191 -> sentence 191
 * NUMBER: 1
 * DESCRIPTION: type inference when a default override remains open for further overriding in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
    fun read(): Int = f()
}

open class Mid : Base() {
    override fun f(): Int = 2
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
}

// TESTCASE NUMBER: 2
open class Meter {
    open val reading: Int = 1
}

open class MidMeter : Meter() {
    override val reading: Int = 2
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
    override fun name(): String = "mid"
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
