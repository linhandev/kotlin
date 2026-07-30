// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 170 -> sentence 170
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 170 -> sentence 170
 *                inheritance, inheriting -> paragraph 170 -> sentence 170
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 170 -> sentence 170
 * NUMBER: 1
 * DESCRIPTION: type inference for inner subclass inheritance that reads outer-instance members through constructor-delegated inner hierarchy
 * HELPERS: checkType
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

fun case1() {
    val child = Outer("x").Child()
    child checkType { check<Outer.Child>() }
    checkSubtype<Outer.Base>(child)
    child.get() checkType { check<String>() }
    child.t() checkType { check<String>() }
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

fun case2() {
    val deep = Box(10).Deep(2, 3)
    deep checkType { check<Box.Deep>() }
    checkSubtype<Box.Layer>(deep)
    deep.step checkType { check<Int>() }
    deep.boost checkType { check<Int>() }
    deep.value() checkType { check<Int>() }
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

fun case3() {
    val leaf = Scope("root").Leaf()
    leaf checkType { check<Scope.Leaf>() }
    checkSubtype<Scope.Branch>(leaf)
    checkSubtype<Scope.Node>(leaf)
    leaf.leafPath() checkType { check<String>() }
    leaf.branchPath() checkType { check<String>() }
    leaf.path() checkType { check<String>() }
}
