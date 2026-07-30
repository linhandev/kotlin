// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 214 -> sentence 214
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 214 -> sentence 214
 *                inheritance, overriding -> paragraph 214 -> sentence 214
 * NUMBER: 1
 * DESCRIPTION: different parameter lists from two interfaces are overloads (not override conflicts); class declaration can implement both; contrasts with same-signature dual-default conflict (p-206) and with erasure clash of List<T> (next neg point)
 */

// TESTCASE NUMBER: 1
interface IntArg {
    fun f(x: Int): Int
}

interface StringArg {
    fun f(s: String): String
}

class DualOverload : IntArg, StringArg {
    override fun f(x: Int): Int = x
    override fun f(s: String): String = s
}

// TESTCASE NUMBER: 2
interface BoolArg {
    fun g(b: Boolean): Boolean
}

interface LongArg {
    fun g(n: Long): Long
}

class DualPrimOverload : BoolArg, LongArg {
    override fun g(b: Boolean): Boolean = !b
    override fun g(n: Long): Long = n + 1L
}

// TESTCASE NUMBER: 3
interface LeftPair {
    fun h(a: Int, b: Int): Int
}

interface RightSingle {
    fun h(a: Int): Int
}

class ArityOverload : LeftPair, RightSingle {
    override fun h(a: Int, b: Int): Int = a + b
    override fun h(a: Int): Int = a * 2
}

fun box(): String {
    if (DualOverload().f(1) != 1) return "NOK: int"
    if (DualOverload().f("hi") != "hi") return "NOK: string"
    val asInt: IntArg = DualOverload()
    if (asInt.f(7) != 7) return "NOK: via-int"
    val asString: StringArg = DualOverload()
    if (asString.f("ok") != "ok") return "NOK: via-string"

    if (DualPrimOverload().g(true) != false) return "NOK: bool"
    if (DualPrimOverload().g(false) != true) return "NOK: bool-false"
    if (DualPrimOverload().g(10L) != 11L) return "NOK: long"
    val asBool: BoolArg = DualPrimOverload()
    if (asBool.g(false) != true) return "NOK: via-bool"
    val asLong: LongArg = DualPrimOverload()
    if (asLong.g(3L) != 4L) return "NOK: via-long"

    if (ArityOverload().h(2, 3) != 5) return "NOK: pair"
    if (ArityOverload().h(4) != 8) return "NOK: single"
    val asPair: LeftPair = ArityOverload()
    if (asPair.h(1, 2) != 3) return "NOK: via-pair"
    val asSingle: RightSingle = ArityOverload()
    if (asSingle.h(5) != 10) return "NOK: via-single"
    return "OK"
}
