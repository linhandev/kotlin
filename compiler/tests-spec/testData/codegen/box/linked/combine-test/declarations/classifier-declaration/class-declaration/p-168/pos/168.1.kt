// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 168 -> sentence 168
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 168 -> sentence 168
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 168 -> sentence 168
 *                declarations, classifier-declaration, interface-declaration -> paragraph 168 -> sentence 168
 * NUMBER: 1
 * DESCRIPTION: the supertype list may place the single class constructor delegation before or among interfaces, and only that class receives constructor arguments in class declaration
 */

// TESTCASE NUMBER: 1
interface I1

interface I2

open class Base(val x: Int)

class Impl : Base(1), I1, I2

// TESTCASE NUMBER: 2
interface Alpha {
    fun alpha(): String
}

interface Beta {
    fun beta(): Int
}

open class Core(val seed: Int)

class MidOrder(private val input: Int) : Alpha, Core(input * 2), Beta {
    override fun alpha(): String = "a$input"
    override fun beta(): Int = input
}

// TESTCASE NUMBER: 3
interface Left {
    fun left(): Int
}

interface Right {
    fun right(): Int
}

open class Parent(val n: Int)

class Child(private val input: Int, val tag: String) : Left, Right, Parent(input + 1) {
    override fun left(): Int = input
    override fun right(): Int = tag.length
}

fun viaImpl(): Triple<Int, Boolean, Boolean> {
    val i = Impl()
    return Triple(i.x, i is I1, i is I2)
}

fun viaMid(): Triple<Int, String, Int> {
    val m = MidOrder(3)
    return Triple(m.seed, m.alpha(), m.beta())
}

fun viaChild(): List<Any> {
    val c = Child(4, "ok")
    return listOf(c.n, c.tag, c.left(), c.right())
}

fun box(): String {
    if (viaImpl() != Triple(1, true, true)) return "NOK: impl"
    if (Impl().x != 1) return "NOK: impl-x"

    if (viaMid() != Triple(6, "a3", 3)) return "NOK: mid"
    if ((MidOrder(5) as Core).seed != 10) return "NOK: mid-as-core"
    if (MidOrder(5) !is Alpha || MidOrder(5) !is Beta) return "NOK: mid-ifaces"

    if (viaChild() != listOf(5, "ok", 4, 2)) return "NOK: child"
    if ((Child(1, "z") as Parent).n != 2) return "NOK: child-as-parent"
    return "OK"
}
