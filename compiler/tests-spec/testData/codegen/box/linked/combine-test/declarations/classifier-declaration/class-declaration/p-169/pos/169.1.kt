// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 169 -> sentence 169
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 169 -> sentence 169
 *                inheritance, inheriting -> paragraph 169 -> sentence 169
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 169 -> sentence 169
 * NUMBER: 1
 * DESCRIPTION: nested (non-inner) subclass inheritance and constructor delegation do not capture an outer instance and are created via Outer.Child() in class declaration
 */

// TESTCASE NUMBER: 1
class Outer {
    open class Base

    class Child : Base()
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

// TESTCASE NUMBER: 3
class Nest {
    open class Parent(val seed: Int)

    open class Mid(seed: Int) : Parent(seed * 2)

    class Leaf(private val input: Int, val extra: Int) : Mid(input) {
        fun total(): Int = input + extra
    }
}

fun viaChild(): Boolean {
    val c: Outer.Base = Outer.Child()
    return c is Outer.Child
}

fun viaLeaf(): Pair<String, String> {
    val plain: Host.Node = Host.Node(1)
    val leaf: Host.Node = Host.Leaf(2, "ok")
    return plain.mark() to leaf.mark()
}

fun viaNest(): List<Int> {
    val leaf = Nest.Leaf(3, 4)
    return listOf(leaf.seed, leaf.extra, leaf.total())
}

fun box(): String {
    if (!viaChild()) return "NOK: child"
    if (Outer.Child() !is Outer.Base) return "NOK: child-base"

    if (viaLeaf() != ("n1" to "l2:ok")) return "NOK: leaf"
    if (Host.Leaf(5, "z").id != 5) return "NOK: leaf-id"
    // Nested Leaf is created without any Host instance.
    if (Host.Leaf(0, "a").mark() != "l0:a") return "NOK: leaf-no-outer"

    if (viaNest() != listOf(6, 4, 7)) return "NOK: nest"
    if ((Nest.Leaf(1, 2) as Nest.Parent).seed != 2) return "NOK: nest-as-parent"
    return "OK"
}
