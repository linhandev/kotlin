// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 168 -> sentence 168
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 168 -> sentence 168
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 168 -> sentence 168
 *                declarations, classifier-declaration, interface-declaration -> paragraph 168 -> sentence 168
 * NUMBER: 1
 * DESCRIPTION: type inference when the single class constructor delegation appears before or among interfaces in the supertype list
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I1

interface I2

open class Base(val x: Int)

class Impl : Base(1), I1, I2

fun case1() {
    val i = Impl()
    i checkType { check<Impl>() }
    checkSubtype<Base>(i)
    checkSubtype<I1>(i)
    checkSubtype<I2>(i)
    i.x checkType { check<Int>() }
}

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

fun case2() {
    val m = MidOrder(3)
    m checkType { check<MidOrder>() }
    checkSubtype<Core>(m)
    checkSubtype<Alpha>(m)
    checkSubtype<Beta>(m)
    m.seed checkType { check<Int>() }
    m.alpha() checkType { check<String>() }
    m.beta() checkType { check<Int>() }
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

fun case3() {
    val c = Child(4, "ok")
    c checkType { check<Child>() }
    checkSubtype<Parent>(c)
    checkSubtype<Left>(c)
    checkSubtype<Right>(c)
    c.n checkType { check<Int>() }
    c.tag checkType { check<String>() }
}
