// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 170 -> sentence 170
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 170 -> sentence 170
 *                inheritance, inheriting -> paragraph 170 -> sentence 170
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 170 -> sentence 170
 * NUMBER: 1
 * DESCRIPTION: inner subclass inheritance via constructor delegation can read outer-instance members through the inherited inner hierarchy in class declaration
 */

// TESTCASE NUMBER: 1
class Outer(val tag: String) {
    inner open class Base {
        fun t(): String = tag
    }

    inner class Child : Base() {
        fun get(): String = t()
    }
}

// TESTCASE NUMBER: 2
class Box(val seed: Int) {
    inner open class Layer(val step: Int) {
        open fun value(): Int = seed + step
    }

    inner class Deep(step: Int, val boost: Int) : Layer(step) {
        override fun value(): Int = seed + step + boost
    }
}

// TESTCASE NUMBER: 3
class Scope(val name: String) {
    inner open class Node {
        open fun path(): String = name
    }

    inner open class Branch : Node() {
        fun branchPath(): String = path() + "/b"
    }

    inner class Leaf : Branch() {
        fun leafPath(): String = path() + "/l"
    }
}

fun viaChild(): String = Outer("x").Child().get()

fun viaDeep(): Pair<Int, Int> {
    val box = Box(10)
    val layer: Box.Layer = box.Layer(1)
    val deep: Box.Layer = box.Deep(2, 3)
    return layer.value() to deep.value()
}

fun viaScope(): List<String> {
    val s = Scope("root")
    return listOf(s.Branch().branchPath(), s.Leaf().leafPath(), (s.Leaf() as Scope.Node).path())
}

fun box(): String {
    if (viaChild() != "x") return "NOK: child"
    if (Outer("ab").Child().t() != "ab") return "NOK: child-t"
    if (Outer("z").Child() !is Outer.Base) return "NOK: child-is-base"

    if (viaDeep() != (11 to 15)) return "NOK: deep"
    if (Box(1).Deep(0, 4).value() != 5) return "NOK: deep-direct"
    if (Box(7).Layer(2).value() != 9) return "NOK: layer"

    if (viaScope() != listOf("root/b", "root/l", "root")) return "NOK: scope"
    if (Scope("a").Leaf().branchPath() != "a/b") return "NOK: leaf-branch"
    return "OK"
}
