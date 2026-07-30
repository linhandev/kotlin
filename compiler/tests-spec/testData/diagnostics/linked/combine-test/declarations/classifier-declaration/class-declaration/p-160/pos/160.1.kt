// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 160 -> sentence 160
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 160 -> sentence 160
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 160 -> sentence 160
 *                inheritance, inheriting -> paragraph 160 -> sentence 160
 * NUMBER: 1
 * DESCRIPTION: type inference for superclass primary-constructor-driven property and init completing before subclass property and init after constructor delegation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(seed: String) {
    val log = StringBuilder().apply { append(seed) }
    init {
        log.append("I")
    }
}

class Child(seed: String) : Base(seed) {
    val mid = log.append("P").toString().length
    init {
        log.append("C")
    }
}

fun case1() {
    val c = Child("B")
    c checkType { check<Child>() }
    checkSubtype<Base>(c)
    c.log checkType { check<StringBuilder>() }
    c.mid checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class LayerA(mark: Char) {
    val steps = mutableListOf<Char>()
    init {
        steps += mark
        steps += 'A'
    }
}

class LayerB(mark: Char) : LayerA(mark) {
    init {
        steps += 'B'
    }
}

fun case2() {
    val b = LayerB('Z')
    b checkType { check<LayerB>() }
    checkSubtype<LayerA>(b)
    b.steps checkType { check<MutableList<Char>>() }
}

// TESTCASE NUMBER: 3
open class Root(val n: Int) {
    val doubled: Int
    init {
        doubled = n * 2
    }
}

class Leaf(n: Int, val tag: String) : Root(n) {
    val summary: String
    init {
        summary = "$doubled:$tag"
    }
}

fun case3() {
    val leaf = Leaf(3, "t")
    leaf checkType { check<Leaf>() }
    checkSubtype<Root>(leaf)
    leaf.n checkType { check<Int>() }
    leaf.doubled checkType { check<Int>() }
    leaf.summary checkType { check<String>() }
    leaf.tag checkType { check<String>() }
}
