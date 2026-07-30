// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 152 -> sentence 152
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 152 -> sentence 152
 *                inheritance, inheriting -> paragraph 152 -> sentence 152
 *                declarations, classifier-declaration, interface-declaration -> paragraph 152 -> sentence 152
 * NUMBER: 1
 * DESCRIPTION: interfaces in the supertype list do not participate in constructor delegation; only the class supertype is delegated in class declaration
 */

// TESTCASE NUMBER: 1
interface I

open class Base(val x: Int)

class Impl(x: Int) : Base(x), I

// TESTCASE NUMBER: 2
interface Tagged {
    fun tag(): String
}

open class Store(val seed: Int)

class TaggedStore(seed: Int) : Store(seed), Tagged {
    override fun tag(): String = "s$seed"
}

// TESTCASE NUMBER: 3
interface A {
    fun a(): Int
}

interface B {
    fun b(): Int
}

open class Root(val n: Int)

class Multi(n: Int) : Root(n), A, B {
    override fun a(): Int = n
    override fun b(): Int = n * 2
}

fun viaImpl(): Pair<Int, Boolean> {
    val i = Impl(3)
    return i.x to (i is I)
}

fun viaTagged(): Pair<Int, String> {
    val t = TaggedStore(5)
    return t.seed to t.tag()
}

fun viaMulti(): List<Int> {
    val m = Multi(4)
    return listOf(m.n, m.a(), m.b())
}

fun box(): String {
    if (viaImpl() != (3 to true)) return "NOK: impl"
    if (Impl(9).x != 9) return "NOK: impl-9"
    if (viaTagged() != (5 to "s5")) return "NOK: tagged"
    if (viaMulti() != listOf(4, 4, 8)) return "NOK: multi"
    if (Multi(1) !is A || Multi(1) !is B || Multi(1) !is Root) return "NOK: multi-types"
    return "OK"
}
