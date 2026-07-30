// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 173 -> sentence 173
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 173 -> sentence 173
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 173 -> sentence 173
 * NUMBER: 1
 * DESCRIPTION: type inference for subclass secondary constructor : super(...) without a primary constructor
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child : Base {
    constructor(v: Int) : super(v)
}

fun case1() {
    val c = Child(5)
    c checkType { check<Child>() }
    checkSubtype<Base>(c)
    c.x checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class PairBase(val a: Int, val b: Int)

class PairChild : PairBase {
    constructor(v: Int) : super(v, v + 1)
    constructor(left: Int, right: Int) : this(left + right)
}

fun case2() {
    val one = PairChild(3)
    one checkType { check<PairChild>() }
    checkSubtype<PairBase>(one)
    one.a checkType { check<Int>() }
    one.b checkType { check<Int>() }

    val two = PairChild(2, 4)
    two checkType { check<PairChild>() }
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

fun case3() {
    val m = Marked(4)
    m checkType { check<Marked>() }
    checkSubtype<Host>(m)
    checkSubtype<Marker>(m)
    m.n checkType { check<Int>() }
    m.mark() checkType { check<String>() }
}
