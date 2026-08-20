// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 153 -> sentence 153
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 153 -> sentence 153
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 153 -> sentence 153
 *                declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 153 -> sentence 153
 * NUMBER: 1
 * DESCRIPTION: abstract class as direct superclass requires subclass constructor delegation type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
abstract class Base(val id: Int)

class Impl : Base(1)

fun case1() {
    val i = Impl()
    i checkType { check<Impl>() }
    checkSubtype<Base>(i)
    i.id checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
abstract class Named(val label: String) {
    abstract fun tag(): String
}

class NamedImpl(label: String) : Named(label) {
    override fun tag(): String = "t$label"
}

fun case2() {
    val n = NamedImpl("ok")
    n checkType { check<NamedImpl>() }
    checkSubtype<Named>(n)
    n.label checkType { check<String>() }
    n.tag() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface Marker {
    fun mark(): Int
}

abstract class Store(val seed: Int) {
    abstract fun value(): Int
}

class MarkedStore(seed: Int) : Store(seed), Marker {
    override fun value(): Int = seed * 2
    override fun mark(): Int = seed
}

fun case3() {
    val m = MarkedStore(4)
    m checkType { check<MarkedStore>() }
    checkSubtype<Store>(m)
    checkSubtype<Marker>(m)
    m.seed checkType { check<Int>() }
    m.value() checkType { check<Int>() }
    m.mark() checkType { check<Int>() }
}
