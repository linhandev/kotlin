// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 173 -> sentence 173
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 173 -> sentence 173
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 173 -> sentence 173
 * NUMBER: 1
 * DESCRIPTION: subclass secondary constructor delegates to superclass via : super(...) when no primary constructor is declared
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child : Base {
    constructor(v: Int) : super(v)
}

// TESTCASE NUMBER: 2
open class PairBase(val a: Int, val b: Int)

class PairChild : PairBase {
    constructor(v: Int) : super(v, v + 1)
    constructor(left: Int, right: Int) : this(left + right)
}

// TESTCASE NUMBER: 3
interface Marker {
    fun mark(): String
}

open class Host(val n: Int)

class Marked : Host, Marker {
    constructor(v: Int) : super(v * 2)
    override fun mark(): String = "m$n"
}

fun viaChild(): Int = Child(5).x

fun viaPair(): List<Pair<Int, Int>> {
    val one = PairChild(3)
    val two = PairChild(2, 4)
    return listOf(one.a to one.b, two.a to two.b)
}

fun viaMarked(): Triple<Int, String, Boolean> {
    val m = Marked(4)
    return Triple(m.n, m.mark(), m is Marker)
}

fun box(): String {
    if (viaChild() != 5) return "NOK: child"
    if (Child(9).x != 9) return "NOK: child-direct"

    if (viaPair() != listOf(3 to 4, 6 to 7)) return "NOK: pair"
    if (PairChild(1).b != 2) return "NOK: pair-b"

    if (viaMarked() != Triple(8, "m8", true)) return "NOK: marked"
    if ((Marked(5) as Host).n != 10) return "NOK: marked-as-host"
    return "OK"
}
