// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 197 -> sentence 197
 * PRIMARY LINKS: inheritance, overriding -> paragraph 197 -> sentence 197
 *                operator-overloading, overview -> paragraph 197 -> sentence 197
 *                inheritance, inheriting -> paragraph 197 -> sentence 197
 * NUMBER: 1
 * DESCRIPTION: overriding an open operator requires both override and operator; the subclass implementation is used for operator calls and dynamic dispatch
 */

// TESTCASE NUMBER: 1
open class Vec(val x: Int) {
    open operator fun plus(o: Vec): Vec = Vec(x + o.x)
}

class V2(x: Int) : Vec(x) {
    override operator fun plus(o: Vec): Vec = V2(x + o.x + 10)
}

// TESTCASE NUMBER: 2
open class Scale(val n: Int) {
    open operator fun times(k: Int): Scale = Scale(n * k)
}

class StrictScale(n: Int) : Scale(n) {
    override operator fun times(k: Int): Scale = StrictScale(n * k + 1)
}

// TESTCASE NUMBER: 3
open class Counter(val value: Int) {
    open operator fun unaryMinus(): Counter = Counter(-value)
    fun mirrored(): Counter = -this
}

class OffsetCounter(value: Int) : Counter(value) {
    override operator fun unaryMinus(): Counter = OffsetCounter(-value - 1)
}

fun box(): String {
    val sum = V2(1) + V2(2)
    if (sum.x != 13) return "NOK: v2-plus" // 1+2+10
    if (sum !is V2) return "NOK: v2-runtime"
    if ((Vec(1) + Vec(2)).x != 3) return "NOK: vec-plus"
    val asVec: Vec = V2(4)
    val viaBase = asVec + Vec(1)
    if (viaBase.x != 15) return "NOK: base-ref-plus" // 4+1+10
    if (viaBase !is V2) return "NOK: base-ref-runtime"

    val scaled = StrictScale(3) * 2
    if (scaled.n != 7) return "NOK: strict-times" // 3*2+1
    if (scaled !is StrictScale) return "NOK: strict-runtime"
    if ((Scale(3) * 2).n != 6) return "NOK: scale-times"
    if (((StrictScale(3) as Scale) * 2).n != 7) return "NOK: scale-ref"

    if ((-Counter(5)).value != -5) return "NOK: counter-unary"
    if ((-OffsetCounter(5)).value != -6) return "NOK: offset-unary"
    if (OffsetCounter(5).mirrored().value != -6) return "NOK: mirrored"
    if ((OffsetCounter(5) as Counter).mirrored().value != -6) return "NOK: mirrored-ref"
    return "OK"
}
