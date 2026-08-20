// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 169 -> sentence 169
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 169 -> sentence 169
 *                inheritance, inheriting -> paragraph 169 -> sentence 169
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 169 -> sentence 169
 * NUMBER: 1
 * DESCRIPTION: type inference for nested (non-inner) subclass inheritance created without an outer instance via Outer.Child()
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    open class Base

    class Child : Base()
}

fun case1() {
    val c = Outer.Child()
    c checkType { check<Outer.Child>() }
    checkSubtype<Outer.Base>(c)
    val asBase: Outer.Base = c
    asBase checkType { check<Outer.Base>() }
}

// TESTCASE NUMBER: 2
class Host(val label: String) {
    open class Node(val id: Int) {
        open fun mark(): String = "n$id"
    }

    class Leaf(id: Int, val tag: String) : Node(id) {
        override fun mark(): String = "l$id:$tag"
    }
}

fun case2() {
    val leaf = Host.Leaf(2, "ok")
    leaf checkType { check<Host.Leaf>() }
    checkSubtype<Host.Node>(leaf)
    leaf.id checkType { check<Int>() }
    leaf.tag checkType { check<String>() }
    leaf.mark() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
class Nest {
    open class Parent(val seed: Int)

    open class Mid(seed: Int) : Parent(seed * 2)

    class Leaf(private val input: Int, val extra: Int) : Mid(input) {
        fun total(): Int = input + extra
    }
}

fun case3() {
    val leaf = Nest.Leaf(3, 4)
    leaf checkType { check<Nest.Leaf>() }
    checkSubtype<Nest.Mid>(leaf)
    checkSubtype<Nest.Parent>(leaf)
    leaf.seed checkType { check<Int>() }
    leaf.extra checkType { check<Int>() }
    leaf.total() checkType { check<Int>() }
}
