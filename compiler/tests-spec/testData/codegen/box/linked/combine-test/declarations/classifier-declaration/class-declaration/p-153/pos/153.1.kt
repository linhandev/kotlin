// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 153 -> sentence 153
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 153 -> sentence 153
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 153 -> sentence 153
 *                declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 153 -> sentence 153
 * NUMBER: 1
 * DESCRIPTION: abstract class as direct superclass requires subclass constructor delegation to an accessible abstract-class constructor in class declaration
 */

// TESTCASE NUMBER: 1
abstract class Base(val id: Int)

class Impl : Base(1)

// TESTCASE NUMBER: 2
abstract class Named(val label: String) {
    abstract fun tag(): String
}

class NamedImpl(label: String) : Named(label) {
    override fun tag(): String = "t$label"
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

fun viaImpl(): Pair<Int, Boolean> {
    val i = Impl()
    return i.id to (i is Base)
}

fun viaNamed(): Pair<String, String> {
    val n = NamedImpl("ok")
    return n.label to n.tag()
}

fun viaMarked(): List<Int> {
    val m = MarkedStore(4)
    return listOf(m.seed, m.value(), m.mark())
}

fun box(): String {
    if (viaImpl() != (1 to true)) return "NOK: impl"
    if (Impl().id != 1) return "NOK: impl-id"
    if (viaNamed() != ("ok" to "tok")) return "NOK: named"
    if (NamedImpl("x").tag() != "tx") return "NOK: named-x"
    if (viaMarked() != listOf(4, 8, 4)) return "NOK: marked"
    if (MarkedStore(1) !is Store || MarkedStore(1) !is Marker) return "NOK: marked-types"
    return "OK"
}
