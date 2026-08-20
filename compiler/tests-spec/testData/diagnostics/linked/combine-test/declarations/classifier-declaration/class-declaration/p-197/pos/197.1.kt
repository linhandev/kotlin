// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 197 -> sentence 197
 * PRIMARY LINKS: inheritance, overriding -> paragraph 197 -> sentence 197
 *                operator-overloading, overview -> paragraph 197 -> sentence 197
 *                inheritance, inheriting -> paragraph 197 -> sentence 197
 * NUMBER: 1
 * DESCRIPTION: type inference when an open operator is overridden with override operator in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Vec(val x: Int) {
    open operator fun plus(o: Vec): Vec = Vec(x + o.x)
}

class V2(x: Int) : Vec(x) {
    override operator fun plus(o: Vec): Vec = V2(x + o.x + 10)
}

fun case1() {
    val v = V2(1)
    v checkType { check<V2>() }
    checkSubtype<Vec>(v)
    (v + V2(2)) checkType { check<Vec>() }

    val asVec: Vec = v
    (asVec + Vec(1)) checkType { check<Vec>() }
}

// TESTCASE NUMBER: 2
open class Scale(val n: Int) {
    open operator fun times(k: Int): Scale = Scale(n * k)
}

class StrictScale(n: Int) : Scale(n) {
    override operator fun times(k: Int): Scale = StrictScale(n * k + 1)
}

fun case2() {
    val s = StrictScale(3)
    s checkType { check<StrictScale>() }
    checkSubtype<Scale>(s)
    (s * 2) checkType { check<Scale>() }
}

// TESTCASE NUMBER: 3
open class Counter(val value: Int) {
    open operator fun unaryMinus(): Counter = Counter(-value)
}

class OffsetCounter(value: Int) : Counter(value) {
    override operator fun unaryMinus(): Counter = OffsetCounter(-value - 1)
}

fun case3() {
    val c = OffsetCounter(5)
    c checkType { check<OffsetCounter>() }
    checkSubtype<Counter>(c)
    (-c) checkType { check<Counter>() }
}
